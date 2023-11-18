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
import com.example.mobdev_nhom7.models.responseObj.cityName.CityName;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityNameResponseData;
import com.example.mobdev_nhom7.models.responseObj.hotel.HotelResponseObj;
import com.example.mobdev_nhom7.models.responseObj.hotelName.HotelName;
import com.example.mobdev_nhom7.models.responseObj.hotelName.HotelNameResponseData;
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
    private String user_id;
    private String start_date;
    private String end_date;
    private String number_room;
    private String number_guest;
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
        buttonSearch.setOnClickListener(view1 -> loadHotels(desInput.getText().toString(),
                user_id,
                start_date,
                end_date,
                number_guest,
                number_room));

        return view;
    }


    public void loadHotels(String destination, String user_id, String start_date, String end_date, String number_guest, String number_room) {
        Call<SearchHotelResponseData> callHotel = apiService.searchHotels(destination,
                user_id,
                start_date, end_date, number_guest, number_room);
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

    public void callHotelName() {
        Call<HotelNameResponseData> hotelNameResponseDataCall = apiService.getAllHotelName();
        hotelNameResponseDataCall.enqueue(new Callback<HotelNameResponseData>() {
            @Override
            public void onResponse(Call<HotelNameResponseData> call, Response<HotelNameResponseData> response) {
                switch (response.code()) {
                    case 200:
                        List<HotelName> hotelNameList = response.body().getData();
                }
            }

            @Override
            public void onFailure(Call<HotelNameResponseData> call, Throwable t) {
                Toast.makeText(getContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
            }
        });
    };

    public void callCityName() {
        Call<CityNameResponseData> cityNameResponseDataCall = apiService.getAllCityName();
        cityNameResponseDataCall.enqueue(new Callback<CityNameResponseData>() {
            @Override
            public void onResponse(Call<CityNameResponseData> call, Response<CityNameResponseData> response) {
                switch (response.code()) {
                    case 200:
                        List<CityName> cityNameList = response.body().getData();
                }
            }

            @Override
            public void onFailure(Call<CityNameResponseData> call, Throwable t) {
                Toast.makeText(getContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
            }
        });
    };
}
