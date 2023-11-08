package com.example.mobdev_nhom7.fragment.main_activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.models.hotel.HotelItem;
import com.example.mobdev_nhom7.models.hotel.adapters.CardHotelAdapter;
import java.util.ArrayList;

public class FavouritesFragment extends Fragment {
    ArrayList <HotelItem> hotels;
    RecyclerView recyclerView;
    CardHotelAdapter cardHotelAdapter;
    private static final String TAG = "Favourite Fragment";
    private String mParam1;
    public FavouritesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FavouritesFragment newInstance(String param1) {
        FavouritesFragment fragment = new FavouritesFragment();
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
        View v= inflater.inflate(R.layout.fragment_favourites, container, false);

        hotels = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            hotels.add(new HotelItem("Hotel " + (i + 1),
                    "*****",
                    "Ha Noi",
                    "8.5",
                    "Xuat sac",
                    "3456 nhan xet",
                    R.drawable.hotel,
                    " ",
                    " ",
                    " "
            ));
        }
        cardHotelAdapter = new CardHotelAdapter(getActivity(), hotels);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycleView);

        recyclerView.setAdapter(cardHotelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }


}