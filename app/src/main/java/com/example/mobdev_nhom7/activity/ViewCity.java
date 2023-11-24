package com.example.mobdev_nhom7.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mobdev_nhom7.R;
public class ViewCity extends AppCompatActivity {
    private RecyclerView restaurantsRecyclerView;
    private ImageView cityImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_detail);
        restaurantsRecyclerView = findViewById(R.id.restaurantsRV);

        cityImage = findViewById(R.id.cityImage);
        String url = "https://lh3.googleusercontent.com/u/0/drive-viewer/AK7aPaAdUoMDI1hncAN7nJF3wa4QSaAyLts3jyBu2Tc96Z2gbTuPdaWJ2HK0hRA0Sg-uEdz4CdtmWMOYYezle54tnMFe4eaU=w1920-h892";
        Glide.with(this).load(url).centerCrop().into(cityImage);
    }
}