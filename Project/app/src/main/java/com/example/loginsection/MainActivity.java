package com.example.loginsection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.login_button);


        loginButton.setOnClickListener(v -> {
            EditText emailEditText = findViewById(R.id.email_edit_text);
            if (!emailEditText.getText().toString().equals("")) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra("email", emailEditText.getText().toString());
                startActivity(intent);
            }
        });
    }
}