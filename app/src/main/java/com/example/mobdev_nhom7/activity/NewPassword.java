package com.example.mobdev_nhom7.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Pair;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.utils.CustomToast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class NewPassword extends AppCompatActivity {
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private boolean hiddenPassword = true;
    TextView lowerCaseCharacters;
    TextView upperCaseCharacters;

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

        lowerCaseCharacters = findViewById(R.id.lower_case_characters);
        upperCaseCharacters = findViewById(R.id.upper_case_characters);
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lowerCaseCharacters.setText(totalCharacterCount(passwordEditText.getText().toString()) + "/10");
                upperCaseCharacters.setText(uppercaseCount(passwordEditText.getText().toString()) + "/1");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(v -> {
            String email = emailText.getText().toString();
            String password = passwordEditText.getText().toString();

            boolean preCheck = true;
            String preMessage = "";
            String totalChars = lowerCaseCharacters.getText().toString();
            String uppercaseChars = upperCaseCharacters.getText().toString();
            totalChars = totalChars.substring(0, totalChars.indexOf('/'));
            uppercaseChars = uppercaseChars.substring(0, uppercaseChars.indexOf('/'));
            if (!passwordCheck(Integer.parseInt(totalChars), Integer.parseInt(uppercaseChars))) {
                preCheck = false;
                preMessage = "Mật khẩu phải có tối thiểu 10 kí tự và 1 kí tự in hoa";
            }
            if (preCheck) {
                try {
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
                                    CustomToast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT);
                                }
                            });
                } catch(Exception e) {
                    CustomToast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
                }
            } else {
                Toast.makeText(this, preMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static int totalCharacterCount(String password) {
        return password.length();
    }

    public static int uppercaseCount(String password) {
        int dem = 0;
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                dem++;
            }
        }
        return dem;
    }

    public static boolean passwordCheck(int totalChars, int uppercaseChars) {
        if (totalChars < 10 | uppercaseChars < 1) {
            return false;
        }
        return true;
    }
}