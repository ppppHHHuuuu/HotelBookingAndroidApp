package com.example.mobdev_nhom7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class PasswordReset extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        Intent message = getIntent();

        TextView emailText = findViewById(R.id.email_text);
        emailText.setText(message.getStringExtra("email"));

        LinearLayout backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());


        mAuth.sendPasswordResetEmail(emailText.getText().toString())
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        Button resendButton = findViewById(R.id.resend_button);
        TextView resendEmailText = findViewById(R.id.resend_time_count_text);
        resendButton.setEnabled(false);
        new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                int secondsRemaining = (int) millisUntilFinished / 1000;
                resendEmailText.setText("Gửi lại sau " + secondsRemaining);
            }

            public void onFinish() {
                resendButton.setEnabled(true);
                resendButton.setOnClickListener(v -> {
                    mAuth.sendPasswordResetEmail(emailText.getText().toString())
                            .addOnCompleteListener(task -> {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(PasswordReset.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                    resendButton.setEnabled(false);
                    new CountDownTimer(30000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            int secondsRemaining = (int) millisUntilFinished / 1000;
                            resendEmailText.setText("Gửi lại sau " + secondsRemaining);
                        }

                        public void onFinish() {
                            resendButton.setEnabled(true);
                        }
                    }.start();

                });
            }
        }.start();


    }
}