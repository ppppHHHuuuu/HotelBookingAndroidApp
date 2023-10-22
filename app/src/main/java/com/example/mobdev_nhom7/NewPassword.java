package com.example.mobdev_nhom7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class NewPassword extends AppCompatActivity {
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private boolean hiddenPassword = true;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        Intent message = getIntent();

        TextView emailText = findViewById(R.id.email_text);
        emailText.setText(message.getStringExtra("email"));

        LinearLayout backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());

        EditText passwordEditText = findViewById(R.id.password_edit_text);

        ImageView hideImage = findViewById(R.id.hide_image);
        hideImage.setOnClickListener(v -> {
            if (!hiddenPassword) {
                hideImage.setImageResource(R.drawable.hide_eye);
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            } else {
                hideImage.setImageResource(R.drawable.unhide_eye);
                passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }

            passwordEditText.setSelection(passwordEditText.getText().length());
            hiddenPassword = !hiddenPassword;
        });
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pair<Integer, Integer> characterCount = passwordCheck(passwordEditText.getText().toString());
                TextView lowerCaseCharacters = findViewById(R.id.lower_case_characters);
                lowerCaseCharacters.setText(characterCount.first.toString() + "/10");
                TextView upperCaseCharacters = findViewById(R.id.upper_case_characters);
                upperCaseCharacters.setText(characterCount.second.toString() + "/1");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(v -> {
            String email = emailText.getText().toString();
            String password = passwordEditText.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password)
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
                            Log.w("Login Status", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(NewPassword.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private Pair<Integer, Integer> passwordCheck(String password) {
        int dem1 = password.length();
        int dem2 = 0;
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                dem2++;
            }
        }
        return new Pair(dem1, dem2);
    }
}