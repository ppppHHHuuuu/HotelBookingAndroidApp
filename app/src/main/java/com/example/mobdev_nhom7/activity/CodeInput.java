package com.example.mobdev_nhom7.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.utils.CustomToast;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class CodeInput extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String phoneNumber;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private int selectedETPosition = 0;
    private boolean resendEnabled = false;
    private int resendTime = 60;
    private TextView resendButton;
    private EditText[] otpETs = new EditText[6];
    private String getOtp = "";
    PhoneAuthOptions options;
    TextView resendTimeCountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_input);
        Intent message = getIntent();
        phoneNumber = message.getStringExtra("phone_number");

        TextView phoneText = findViewById(R.id.phone_text);
        phoneText.setText(phoneNumber);

        LinearLayout backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());

        otpETs[0] = findViewById(R.id.otp_1);
        otpETs[1] = findViewById(R.id.otp_2);
        otpETs[2] = findViewById(R.id.otp_3);
        otpETs[3] = findViewById(R.id.otp_4);
        otpETs[4] = findViewById(R.id.otp_5);
        otpETs[5] = findViewById(R.id.otp_6);

        for (int i = 0; i < 6; i++) {
            otpETs[i].addTextChangedListener(textWatcher);
        }

        // By default open keyboard on first EditText
        showKeyboard(otpETs[0]);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(v -> {
            getOtp = "";
            for (int i = 0; i < 6; i++) {
                getOtp += otpETs[i].getText().toString();
            }

            if (getOtp.length() == 6) {
                if (resendEnabled) {
                    Toast.makeText(CodeInput.this, "Mã xác thực đã hết hạn", Toast.LENGTH_SHORT).show();
                } else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, getOtp);
                    phoneAuth(credential);
                }
            } else {
                Toast.makeText(CodeInput.this, "Mã xác thực phải có 6 chữ số", Toast.LENGTH_SHORT).show();
            }
        });

        // Phone Auth Handle
        setUpCallBacks();
        options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(CodeInput.this)
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

        startCountDownTimer();
        resendButton = findViewById(R.id.resend_button);
        resendButton.setPaintFlags(resendButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        resendButton.setOnClickListener(v -> {
            if (resendEnabled) {
                PhoneAuthProvider.verifyPhoneNumber(options);
                startCountDownTimer();
            }
        });

        resendTimeCountText = findViewById(R.id.resend_time_count_text);
    }

    private void setUpCallBacks() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.d("Error message", e.getMessage());
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Log.d("Error code1", ((FirebaseAuthInvalidCredentialsException) e).getErrorCode());
                    CustomToast.makeText(CodeInput.this, ((FirebaseAuthInvalidCredentialsException) e).getErrorCode(), Toast.LENGTH_SHORT);
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    Log.d("Error code2", ((FirebaseAuthInvalidCredentialsException) e).getErrorCode());
                    CustomToast.makeText(CodeInput.this, ((FirebaseAuthInvalidCredentialsException) e).getErrorCode(), Toast.LENGTH_SHORT);
                } else if (e instanceof FirebaseAuthMissingActivityForRecaptchaException) {
                    // reCAPTCHA verification attempted with null Activity
                    Log.d("Error code3", ((FirebaseAuthInvalidCredentialsException) e).getErrorCode());
                    CustomToast.makeText(CodeInput.this, ((FirebaseAuthInvalidCredentialsException) e).getErrorCode(), Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                Log.d("onCodeSent", "onCodeSent:" + verificationId);

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0) {
                if (selectedETPosition != 5) {
                    selectedETPosition = (selectedETPosition + 1) % 6;
                    showKeyboard(otpETs[selectedETPosition]);
                }
            }
        }
    };

    private void showKeyboard(EditText otpET) {
        otpET.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(otpET, InputMethodManager.SHOW_IMPLICIT);
    }

    private void startCountDownTimer() {
        resendEnabled = false;
        new CountDownTimer(resendTime * 1000L, 1000) {
            @Override
            public void onTick(long sendsUntilFinished) {
                resendTimeCountText.setText("Có thể gửi lại mã xác thực sau: " + String.valueOf(sendsUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                resendEnabled = true;
            }
        }.start();
    }

    @Override
    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if (selectedETPosition > 0) {
                selectedETPosition--;
            }
            showKeyboard(otpETs[selectedETPosition]);
        }
        return true;
    }

    private void phoneAuth(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Intent intent = new Intent(CodeInput.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Log.d("Error", task.getException().getMessage());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            CustomToast.makeText(CodeInput.this, task.getException().getMessage(), Toast.LENGTH_SHORT);
                        }
                    }
                });
    }
}