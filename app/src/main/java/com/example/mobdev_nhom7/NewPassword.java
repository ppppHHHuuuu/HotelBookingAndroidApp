package com.example.mobdev_nhom7;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class NewPassword extends AppCompatActivity {
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();

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
//                            Toast.makeText(NewPassword.this, "createUserWithEmail:success",
//                                    Toast.LENGTH_SHORT).show();
//                            FirebaseUser user = mAuth.getCurrentUser();

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