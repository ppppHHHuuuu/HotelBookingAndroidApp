package com.example.mobdev_nhom7.fragment.main_activity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
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
    SharedPreferences preferences;
    CardHotelAdapter cardHotelAdapter;
    ArrayList<SearchHotelItem> hotelItemList = new ArrayList<>();
    RecyclerView recyclerView;
    String user_id;
//    String user_id = "1";

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

        cardHotelAdapter = new CardHotelAdapter(requireContext(), hotelItemList);
        cardHotelAdapter.setOnItemClickListener(position -> {
            SearchHotelItem deletedItem = cardHotelAdapter.getData(position);
            deleteFavouriteHotel(user_id, deletedItem.getHotelId(), position);
        });
        preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        user_id = preferences.getString("user_id", "empty user_id");

        recyclerView = (RecyclerView) v.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(cardHotelAdapter);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getFavouriteHotel();
    }

    public void getFavouriteHotel() {
        //fake data
        Call<List<SearchHotelItem>> call = apiService.getFavouriteHotel(user_id);
        String requestUrl = call.request().url().toString();
        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<List<SearchHotelItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<SearchHotelItem>> call, @NonNull Response<List<SearchHotelItem>> response) {
                if (response.isSuccessful()) {
                    if  (response.body() == null) {
                        Log.d("Content", "Empty cntent");
                        Toast.makeText(getContext(), "Empty content", Toast.LENGTH_LONG).show();
                    }
                    // Clear the existing list and add the new items
                    hotelItemList.clear();
                    hotelItemList.addAll(response.body());
                    cardHotelAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<SearchHotelItem>> call, Throwable t) {
                Log.d("call", t.toString());
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void deleteFavouriteHotel(String user_id, String hotel_id, int position) {
        //fake data
//        String user_id = preferences.getString("user_id", "empty user_id");
        Call<String> call = apiService.deleteFavouriteHotel(user_id, hotel_id);
        String requestUrl = call.request().url().toString();

        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

                if (response.isSuccessful()) {
                    hotelItemList.remove(position);
                    cardHotelAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("call", t.toString());
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}