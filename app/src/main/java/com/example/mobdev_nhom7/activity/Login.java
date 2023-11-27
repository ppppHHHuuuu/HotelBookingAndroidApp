package com.example.mobdev_nhom7.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
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
    private APIService apiService = APIUtils.getUserService();

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
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    public static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+)\\.([A-Za-z]{2,4})$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}