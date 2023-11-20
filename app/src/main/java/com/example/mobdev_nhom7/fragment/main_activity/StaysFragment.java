package com.example.mobdev_nhom7.fragment.main_activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.models.hotel.adapters.CardHotel2Adapter;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityName;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityNameResponseData;
import com.example.mobdev_nhom7.models.responseObj.hotelName.HotelNameResponseData;
import com.example.mobdev_nhom7.models.responseObj.search.SearchCityItem;
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
    ArrayList<SearchCityItem> cityItemList = new ArrayList<>();
    private Button buttonSearch;
    private EditText desInput;
    private TextView roomsDisplay;
    private TextView pplDisplay;
    private TextView startDateDisplay;
    private TextView endDateDisplay;
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
        startDateDisplay = view.findViewById(R.id.startDateDisplay);
        endDateDisplay = view.findViewById(R.id.endDateDisplay);
        roomsDisplay = view.findViewById(R.id.roomsDisplay);
        recyclerView = view.findViewById(R.id.recyclerView);
        pplDisplay = view.findViewById(R.id.pplDisplay);
        hotelItemList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setVisibility(View.GONE);

//        findCitySuggestion();
        buttonSearch = view.findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(view1 -> loadHotels(
                desInput.getText().toString(),
                startDateDisplay.getText().toString(),
                endDateDisplay.getText().toString(),
                Integer.parseInt(getFirstNumberFromString(roomsDisplay.getText().toString())),
                Integer.parseInt(getFirstNumberFromString(pplDisplay.getText().toString()))));

        //NOTE: for pop up screen select hotel
        desInput.setOnClickListener(view1 -> getSuggestedDestinationActivity());
        return view;
    }
    public static String getFirstNumberFromString(String word) {
        return word.substring(0, 1);
    }

    public void getSuggestedDestinationActivity() {
        Intent intent = new Intent(getContext(), SuggestedDestinationActivity.class);
        startActivity(intent);
    }

    public void loadHotels(String destination, String start_date, String end_date, Integer numberRoom, Integer numberPpl) {
        //TODO: HOTELID
        String hotel_id = "bdsfgkjdhg";
        //TODO: Dates, ROOM PARSER
        Call<SearchHotelResponseData> callHotel = apiService.searchHotels(
                hotel_id,destination, start_date, end_date, numberRoom, numberPpl);
        callHotel.enqueue(new Callback<SearchHotelResponseData>() {
            @Override
            public void onResponse(Call<SearchHotelResponseData> call, Response<SearchHotelResponseData> response) {

                switch (response.code()) {
                    case 200:
                        if (response.body() != null) {
                            Log.d("Content", "Empty content");
                            Toast.makeText(getContext(), "Empty content", Toast.LENGTH_LONG).show();
                            break;
                        }
                        List<SearchHotelItem> searchHotelItems = response.body().getData();
                        if (searchHotelItems.size() == 0) {
                            Log.d("Content", "Empty Search Result");
                            Toast.makeText(getContext(), "Empty Search Result", Toast.LENGTH_LONG).show();
                            break;
                        }
                        hotelItemList= (ArrayList<SearchHotelItem>) response.body().getData();
                        cardHotel2Adapter = new CardHotel2Adapter(hotelItemList);
                        recyclerView.setAdapter(cardHotel2Adapter);
                        recyclerView.setVisibility(View.VISIBLE);
                    case 404:
                        Log.d("Error", "No accepted route error");
                }
            }

            @Override
            public void onFailure(Call<SearchHotelResponseData> call, Throwable t) {
                Log.d("call", t.toString());
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        } );
    }

//    public void findCitySuggestion() {
//        Call<CityNameResponseData> hotelNameResponseDataCall = apiService.getSuggestedCity();
//        hotelNameResponseDataCall.enqueue(new Callback<CityNameResponseData>() {
//            @Override
//            public void onResponse(Call<CityNameResponseData> call, Response<CityNameResponseData> response) {
//                switch (response.code()) {
//                    case 200:
//                        hotelNameList = response.body().getData();
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<HotelNameResponseData> call, Throwable t) {
//                Toast.makeText(getContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
//            }
//        });
//    };
//
//    public void callCityName() {
//        Call<CityNameResponseData> cityNameResponseDataCall = apiService.getAllCityName();
//        cityNameResponseDataCall.enqueue(new Callback<CityNameResponseData>() {
//            @Override
//            public void onResponse(Call<CityNameResponseData> call, Response<CityNameResponseData> response) {
//                switch (response.code()) {
//                    case 200:
//                        List<CityName> cityNameList = response.body().getData();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CityNameResponseData> call, Throwable t) {
//                Toast.makeText(getContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
//            }
//        });
//    };
}
