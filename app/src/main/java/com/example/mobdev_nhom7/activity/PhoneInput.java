package com.example.mobdev_nhom7.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mobdev_nhom7.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneInput extends AppCompatActivity {
    private EditText phoneEditText;
    private Button continueButton;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_input);

        LinearLayout backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {
            finish();
        });

        phoneEditText = findViewById(R.id.phone_edit_text);
        continueButton = findViewById(R.id.continue_button);
        continueButton.setOnClickListener(v -> {
            phoneNumber = phoneEditText.getText().toString();
            boolean phoneValidation;
            if (phoneNumber.startsWith("+")) {
                phoneValidation = isValidVietnamesePhoneNumber(phoneNumber.substring(1));
            } else {
                phoneValidation = isValidVietnamesePhoneNumber(phoneNumber);
            }
            if (phoneValidation) {
                if (phoneNumber.startsWith("0")) {
                    phoneNumber = phoneNumber.substring(1);
                    phoneNumber = "+84" + phoneNumber;
                } else if (phoneNumber.startsWith("84")) {
                    phoneNumber = "+" + phoneNumber;
                }
                Intent intent = new Intent(this, CodeInput.class);
                intent.putExtra("phone_number", phoneNumber);
                startActivity(intent);
            } else {
                Toast.makeText(PhoneInput.this, "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private static boolean isValidVietnamesePhoneNumber(String phoneNumber) {
        String regex = "^(0|84)\\d{9,10}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }
}