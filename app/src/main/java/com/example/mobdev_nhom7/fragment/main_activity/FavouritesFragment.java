package com.example.mobdev_nhom7.fragment.main_activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.models.hotel.adapters.CardHotel2Adapter;
import com.example.mobdev_nhom7.models.hotel.adapters.CardHotelAdapter;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouritesFragment extends Fragment {
    private APIService apiService = APIUtils.getUserService();
    CardHotelAdapter cardHotelAdapter;
    ArrayList<SearchHotelItem> hotelItemList = new ArrayList<>();


    RecyclerView recyclerView;
    public FavouritesFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_favourites, container, false);

        cardHotelAdapter = new CardHotelAdapter(getContext(), hotelItemList);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycleView);
        recyclerView.setAdapter(cardHotelAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getSuggestDest();
        return v;
    }

    public void getSuggestDest() {
        //fake data
        String user_id = "1";
        Call<List<SearchHotelItem>> call = apiService.getFavouriteHotel(user_id);
        String requestUrl = call.request().url().toString();
        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<List<SearchHotelItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<SearchHotelItem>> call, @NonNull Response<List<SearchHotelItem>> response) {
                if (response.isSuccessful()) {
                    if  (response.body() == null) {
                        Log.d("Content", "Empty content");
                        Toast.makeText(getContext(), "Empty content", Toast.LENGTH_LONG).show();
                    }
                    ArrayList<SearchHotelItem> cityItems = (ArrayList<SearchHotelItem>) response.body();
                    CardHotelAdapter cityItemCardAdapter = new CardHotelAdapter(getContext(), cityItems);
                    recyclerView.setAdapter(cityItemCardAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<SearchHotelItem>> call, Throwable t) {
                Log.d("call", t.toString());
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}