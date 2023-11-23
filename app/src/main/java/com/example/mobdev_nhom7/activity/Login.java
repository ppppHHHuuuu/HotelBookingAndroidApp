package com.example.mobdev_nhom7.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.utils.CustomToast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    GoogleSignInOptions googleSignInOptions;
    private GoogleSignInClient googleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText emailEditText = findViewById(R.id.email_edit_text);
        SharedPreferences sharedPreferences = this.getSharedPreferences(getString(R.string.user_info), MODE_PRIVATE);
        String currentAccount = sharedPreferences.getString("email", "-1");
        if (!currentAccount.equals("-1")) {
            emailEditText.setText(currentAccount);
        }

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(v -> {
            FirebaseUser presentUser = mAuth.getCurrentUser();
            if (presentUser != null) {
                mAuth.signOut();
            }


            String email = emailEditText.getText().toString();

            boolean preCheck = true;
            String preMessage = "";
            if (!isValidEmail(email)) {
                preCheck = false;
                preMessage = "Email không hợp lệ";
            }
            if (preCheck) {
                mAuth.createUserWithEmailAndPassword(email, "!!!!!!")
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                mAuth.signOut();
                                currentUser.delete().addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        Intent intent = new Intent(getApplicationContext(), NewPassword.class);
                                        intent.putExtra("email", emailEditText.getText().toString());
                                        startActivity(intent);
                                    } else {
                                        CustomToast.makeText(this, task1.getException().getMessage(), Toast.LENGTH_SHORT);
                                    }
                                });
                            } else {
                                if (task.getException().getMessage().equals("The email address is already in use by another account.")) {
                                    Intent intent = new Intent(getApplicationContext(), Password.class);
                                    intent.putExtra("email", emailEditText.getText().toString());
                                    startActivity(intent);
                                } else {
                                    CustomToast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT);
                                }
                            }
                        });
            } else {
                Toast.makeText(this, preMessage,
                        Toast.LENGTH_SHORT).show();
            }
        });

        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
        googleSignInClient.signOut();
        Button googleLogin = findViewById(R.id.google_login);
        googleLogin.setOnClickListener(v -> {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {
                mAuth.signOut();
            }
            GoogleSignIn();
        });

        Button phoneLogin = findViewById(R.id.phone_login);
        phoneLogin.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, PhoneInput.class);
            startActivity(intent);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            mAuth.signOut();
        }
        googleSignInClient.signOut();
    }


    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+)\\.([A-Za-z]{2,4})$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private void GoogleSignIn() {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Task<GoogleSignInAccount> account = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount signInAccount = account.getResult(ApiException.class);
                googleAuth(signInAccount.getIdToken());
            } catch (ApiException e) {
                CustomToast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            }
        }
    }

    private void googleAuth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        SharedPreferences sharedPreferences = this.getSharedPreferences(
                                getString(R.string.user_info), Context.MODE_PRIVATE
                        );
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email", user.getEmail());
                        editor.putString("provider", user.getProviderId());
                        editor.apply();

                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        CustomToast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT);
                    }
                });
    }
}