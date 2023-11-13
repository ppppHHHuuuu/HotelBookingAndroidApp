package com.example.mobdev_nhom7.fragment.main_activity.trips;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.models.hotel.adapters.CardHotelAdapter;

import java.util.ArrayList;

public class TripsCancelledFragment extends Fragment {
    ArrayList<String> hotels;
    ArrayList<String> stars;
    ArrayList<String> cities;
    ArrayList<String> scores;
    ArrayList<String> rates;
    ArrayList<String> judges;
    ArrayList<Integer> images;

    RecyclerView recyclerView;

    CardHotelAdapter cardHotelAdapter;
    private static final String TAG = "Favourite Fragment";
    private String mParam1;

    public TripsCancelledFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TripsCancelledFragment newInstance(String param1) {
        TripsCancelledFragment fragment = new TripsCancelledFragment();
        Bundle args = new Bundle();
        args.putString(TAG, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trips_active, container, false);
        // Inflate the layout for this fragment

        // Assign variables to views using findViewById
//        textHotelName = findViewById(R.id.textHotelName);
//        textStarNumber = findViewById(R.id.textStarNumber);
//        textCity = findViewById(R.id.textCity);
//        textScore = findViewById(R.id.textScore);
//        textRate = findViewById(R.id.textRate);
//        textNumberofJudges = findViewById(R.id.textNumberofJudges);
        hotels = new ArrayList<String>() {{
            add("Hotel 1");
            add("Hotel 2");
            add("Hotel 3");
            add("Hotel 4");
            add("Hotel Long Name does it lost");
            add("Hotel 6");
        }};
        stars = new ArrayList<String>() {{
            add("****");
            add("*****");
            add("***");
            add("***");
            add("***");
            add("***");
        }};
        cities = new ArrayList<String>() {
            {
                add("Ha Noi");
                add("Ho Chi Minh");
                add("Da Nang");
                add("Da Nang");
                add("Da Nang");
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
            add("Excellent");
            add("Excellent");
            add("Excellent");
            add("Excellent");
            add("Excellent");
            add("Excellent");
        }};
        judges = new ArrayList<String>() {{
            add("4546 Reviews");
            add("3456 Reviews");
            add("3546 Reviews");
            add("3546 Reviews");
            add("3546 Reviews");
            add("3546 Reviews");
        }};
        images = new ArrayList<Integer>() {{
            add(R.drawable.apple);
            add(R.drawable.apple);
            add(R.drawable.apple);
            add(R.drawable.apple);
            add(R.drawable.apple);
            add(R.drawable.apple);
        }};

        cardHotelAdapter = new CardHotelAdapter(getActivity(), hotels, stars, cities, scores, rates, judges, images);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycleView);

        recyclerView.setAdapter(cardHotelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }
}