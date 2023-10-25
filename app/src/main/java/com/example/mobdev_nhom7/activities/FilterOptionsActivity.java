package com.example.mobdev_nhom7.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.models.hotel.HotelItem;
import com.example.mobdev_nhom7.models.hotel.adapters.CardHotel2Adapter;

import java.util.ArrayList;

public class FilterOptionsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CardHotel2Adapter cardHotel2Adapter;
    ArrayList<HotelItem> hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_options);
        hotels = new ArrayList();

        // Populate the ArrayLists with sample data for six hotels
        for (int i = 0; i < 6; i++) {
            hotels.add(new HotelItem("Hotel " + (i + 1),
                    "4.5",
                    "City " + (i + 1),
                    "8.5",
                    "Very Good",
                    "150",
                    R.drawable.hotel_1,
                    "1.5 km",
                    "2 hours ago",
                    "4500 VND"
            ));
        }
        cardHotel2Adapter = new CardHotel2Adapter(this, hotels);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);

        recyclerView.setAdapter(cardHotel2Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}