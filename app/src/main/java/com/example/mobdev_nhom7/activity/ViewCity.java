package com.example.mobdev_nhom7.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mobdev_nhom7.R;
public class ViewCity extends AppCompatActivity {
    private RecyclerView restaurantsRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_detail);
        restaurantsRecyclerView = findViewById(R.id.restaurantsRV);
    }
}