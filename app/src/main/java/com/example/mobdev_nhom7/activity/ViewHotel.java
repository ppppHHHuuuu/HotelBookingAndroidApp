package com.example.mobdev_nhom7.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import com.example.mobdev_nhom7.R;

public class ViewHotel extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_hotel);
        View price_button1 = findViewById(R.id.button1);
        TextView room1 = (TextView) price_button1.findViewById(R.id.room);
        room1.setText("Doubles Room");
    }
}