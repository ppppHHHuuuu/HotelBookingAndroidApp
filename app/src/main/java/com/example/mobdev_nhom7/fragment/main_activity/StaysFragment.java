package com.example.mobdev_nhom7.fragment.main_activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.FilterOptionsActivity;
import com.example.mobdev_nhom7.models.hotel.adapters.CardHotel2Adapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StaysFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaysFragment extends Fragment {
    CardHotel2Adapter cardHotel2Adapter;
    ArrayList hotels, stars, cities, scores, rates, judges, distance, lastBooking, amount;
    ArrayList<Integer> images; // Add images ArrayList

    private Button buttonSearch;
    private RecyclerView recyclerView;
    public StaysFragment() {
        // Required empty public constructor
    }
    public static StaysFragment newInstance(String param1, String param2) {
        StaysFragment fragment = new StaysFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: Will instead get data from BE not this way
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
        cardHotel2Adapter = new CardHotel2Adapter(getContext(), hotels, stars, cities, scores, rates, judges, images, distance, lastBooking, amount);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_stays, container, false);
        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setAdapter(cardHotel2Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setVisibility(View.GONE);
        buttonSearch = view.findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }
}