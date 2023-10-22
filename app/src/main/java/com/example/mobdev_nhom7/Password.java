package com.example.mobdev_nhom7;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Password extends AppCompatActivity {
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private Boolean hiddenPassword = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
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

        Button registerButton = findViewById(R.id.login_button);
        registerButton.setOnClickListener(v -> {
            String email = emailText.getText().toString();
            String password = passwordEditText.getText().toString();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
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
                            Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        TextView forgot_password_text = findViewById(R.id.forgot_password_text);
        forgot_password_text.setPaintFlags(forgot_password_text.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        forgot_password_text.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), PasswordReset.class);
            intent.putExtra("email", emailText.getText().toString());
            startActivity(intent);
        });
    }
}