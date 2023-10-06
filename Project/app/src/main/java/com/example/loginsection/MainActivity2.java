package com.example.loginsection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent message = getIntent();
        TextView emailText = findViewById(R.id.email_text);
        emailText.setText(message.getStringExtra("email"));
        LinearLayout backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());
    }
}