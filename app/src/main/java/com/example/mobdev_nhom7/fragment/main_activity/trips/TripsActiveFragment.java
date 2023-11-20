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
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import java.util.ArrayList;

public class TripsActiveFragment extends Fragment {
    private APIService apiService = APIUtils.getUserService();
    CardHotelAdapter cardHotelAdapter;
    ArrayList<SearchHotelItem> hotelItemList = new ArrayList<>();
    RecyclerView recyclerView;

    public TripsActiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_trips_active, container, false);

        cardHotelAdapter = new CardHotelAdapter(hotelItemList);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycleView);

        recyclerView.setAdapter(cardHotelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }
    //TODO CALL FROM BE

}