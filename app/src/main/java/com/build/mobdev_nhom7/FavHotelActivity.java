package com.build.mobdev_nhom7;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavHotelActivity extends Activity {

    ArrayList<String> hotels;
    ArrayList <String> stars;
    ArrayList <String> cities;
    ArrayList <String> scores;
    ArrayList <String> rates;
    ArrayList <String> judges;
    ArrayList <Integer> images;

    RecyclerView recyclerView;

    CardHotelAdapter cardHotelAdapter;

    private TextView textHotelName;
    private TextView textStarNumber;
    private TextView textCity;
    private TextView textScore;
    private TextView textRate;
    private TextView textNumberofJudges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_hotel); // Replace "your_layout_xml" with the actual XML file name

        // Assign variables to views using findViewById
//        textHotelName = findViewById(R.id.textHotelName);
//        textStarNumber = findViewById(R.id.textStarNumber);
//        textCity = findViewById(R.id.textCity);
//        textScore = findViewById(R.id.textScore);
//        textRate = findViewById(R.id.textRate);
//        textNumberofJudges = findViewById(R.id.textNumberofJudges);
        hotels = new ArrayList<String>(){{
            add("Hotel 1");
            add("Hotel 2");
            add("Hotel 3");
        }};
        stars = new ArrayList<String>(){{
            add("****");
            add("*****");
            add("***");
        }};
        cities = new ArrayList<String>() {
            {
                add("Ha Noi");
                add("Ho Chi Minh");
                add("Da Nang");
            }
        };
        scores = new ArrayList<String>() {{
           add("8.6");
           add("4.5");
            add("8.9");
            add("8.9");
            add("8.9");
            add("8.9");
        }};
        rates = new ArrayList<String>() {{
            add("Xuat sac");
            add("Xuat sac");
            add("Xuat sac");
            add("Xuat sac");
            add("Xuat sac");
            add("Xuat sac");
        }};
        judges = new ArrayList<String>() {{
            add("4546 nhan xet");
            add("3456 nhan xet");
            add("3546 nhan xet");
            add("3546 nhan xet");
            add("3546 nhan xet");
            add("3546 nhan xet");
        }};
        images = new ArrayList<Integer>() {{
            add(R.drawable.hotel_1);
            add(R.drawable.hotel_2);
            add(R.drawable.hotel_3);
            add(R.drawable.hotel_3);
            add(R.drawable.hotel_3);
            add(R.drawable.hotel_3);
        }};

        cardHotelAdapter = new CardHotelAdapter(FavHotelActivity.this, hotels, stars, cities, scores, rates, judges, images );
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setAdapter(cardHotelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(FavHotelActivity.this));
    }
}
