package com.example.mobdev_nhom7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class FilterOptionsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CardHotel2Adapter cardHotel2Adapter;
    ArrayList hotels, stars, cities, scores, rates, judges, distance, lastBooking, amount;
    ArrayList<Integer> images; // Add images ArrayList
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_options);
        hotels = new ArrayList<>();
        stars = new ArrayList<>();
        cities = new ArrayList<>();
        scores = new ArrayList<>();
        rates = new ArrayList<>();
        judges = new ArrayList<>();
        images = new ArrayList<>();
        distance = new ArrayList<>();
        lastBooking = new ArrayList<>();
        amount = new ArrayList<>();

        // Populate the ArrayLists with sample data for six hotels
        for (int i = 0; i < 6; i++) {
            hotels.add("Hotel " + (i + 1));
            stars.add("4.5");
            cities.add("City " + (i + 1));
            scores.add("8.5");
            rates.add("Very Good");
            judges.add("150");
            images.add(R.drawable.hotel_1); // Replace with your image resource
            distance.add("1.5 km");
            lastBooking.add("2 hours ago");
            amount.add("4500 VND");
        }
        cardHotel2Adapter = new CardHotel2Adapter(this, hotels, stars, cities, scores, rates, judges, images, distance, lastBooking, amount);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);

        recyclerView.setAdapter(cardHotel2Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}