package com.example.mobdev_nhom7.fragment.main_activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.models.hotel.HotelItem;
import com.example.mobdev_nhom7.models.hotel.adapters.CardHotel2Adapter;
import com.example.mobdev_nhom7.models.responseObj.hotel.HotelResponseObj;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelResponseData;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StaysFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StaysFragment extends Fragment {
    private APIService apiService = APIUtils.getUserService();
    CardHotel2Adapter cardHotel2Adapter;
    ArrayList<SearchHotelItem> hotelItemList = new ArrayList<>();
    private Button buttonSearch;
    private EditText desInput;
    private TextView roomsDisplay;
    private TextView dateDisplay;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_stays, container, false);
        desInput = view.findViewById(R.id.desInput);
        dateDisplay = view.findViewById(R.id.dateDisplay);
        roomsDisplay = view.findViewById(R.id.roomsDisplay);
        recyclerView = view.findViewById(R.id.recyclerView);

        hotelItemList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setVisibility(View.GONE);

        buttonSearch = view.findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(view1 -> loadHotels(desInput.getText().toString(), dateDisplay.getText().toString(), roomsDisplay.getText().toString()));

        return view;
    }


    public void loadHotels(String destination, String date, String numberPpl) {
        Call<SearchHotelResponseData> callHotel = apiService.searchHotels(destination, date, numberPpl);
        callHotel.enqueue(new Callback<SearchHotelResponseData>() {
            @Override
            public void onResponse(Call<SearchHotelResponseData> call, Response<SearchHotelResponseData> response) {
                List<SearchHotelItem> searchHotelItems = response.body().getData();
                if (searchHotelItems.size() == 0) {
                    Toast.makeText(getContext(), "NO SEARCH FOUND", Toast.LENGTH_LONG).show();
                }
                hotelItemList= (ArrayList<SearchHotelItem>) response.body().getData();
                cardHotel2Adapter = new CardHotel2Adapter(hotelItemList);
                recyclerView.setAdapter(cardHotel2Adapter);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<SearchHotelResponseData> call, Throwable t) {
                Toast.makeText(getContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
            }
        } );
    }
}
