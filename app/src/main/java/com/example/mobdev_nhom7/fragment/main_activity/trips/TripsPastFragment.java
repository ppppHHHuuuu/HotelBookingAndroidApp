package com.example.mobdev_nhom7.fragment.main_activity.trips;

import android.content.SharedPreferences;
import android.os.Bundle;

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
import com.example.mobdev_nhom7.models.responseObj.trips.CancelledHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.PastHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.adapters.CardHotelCancelledTripAdapter;
import com.example.mobdev_nhom7.models.responseObj.trips.adapters.CardHotelPastTripAdapter;
import com.example.mobdev_nhom7.models.responseObj.trips.PastHotelItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.example.mobdev_nhom7.utils.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripsPastFragment extends Fragment {
    private APIService apiService = APIUtils.getUserService();
    SharedPreferences preferences;

    RecyclerView recyclerView;
    CardHotelPastTripAdapter cardHotelPastTripAdapter;
    ArrayList<PastHotelItem> hotelItemList = new ArrayList<>();

    public TripsPastFragment() {
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
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        cardHotelPastTripAdapter = new CardHotelPastTripAdapter(getContext(), hotelItemList);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycleView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL); // Set the orientation as needed
        recyclerView.setAdapter(cardHotelPastTripAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        getUserRatedPastHotel();
        getUserNotRatedPastHotel();
        return v;
    }
    //TODO CALL FROM BE
    private void getUserRatedPastHotel() {
        //fake-data

        String user_id = preferences.getString("user_id", "empty user_id");
        Log.d("user_id", user_id);
        String dummyUserID = "1";

        Call<List<PastHotelItem>> call = apiService.getRatedReservation(dummyUserID);
        String requestUrl = call.request().url().toString();
        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<List<PastHotelItem>>() {
            @Override
            public void onResponse(Call<List<PastHotelItem>> call, Response<List<PastHotelItem>> response) {
                if (!response.isSuccessful()) {
                    Log.d("response error", String.valueOf(response.code()));
                    return;
                }
                if (response.body() == null) {
                    Log.d("response error", "Empty response");
                    return;
                }
                ArrayList<PastHotelItem> searchHotelItems = (ArrayList<PastHotelItem>) response.body();
                if (searchHotelItems.size() == 0) {
                    Toast.makeText(getContext(), "NO SEARCH FOUND", Toast.LENGTH_LONG).show();
                    //ADD LOADING QUERY
                    return;
                }

                cardHotelPastTripAdapter = new CardHotelPastTripAdapter(getContext(), searchHotelItems);
                recyclerView.setAdapter(cardHotelPastTripAdapter);
                recyclerView.setVisibility(View.VISIBLE);
                Log.e("APIError", "Error code: " + response.code() + ", Message: " + response.message());
            }

            @Override
            public void onFailure(Call<List<PastHotelItem>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
                Log.d("loadHotel",t.toString());
            }
        });
    }
    private void getUserNotRatedPastHotel() {
        //fake-data
        String user_id = preferences.getString("user_id", "empty user_id");
        Log.d("user_id", user_id);
        String dummyUserID = "1";

        Call<List<PastHotelItem>> call = apiService.getNotRatedReservation(dummyUserID);
        String requestUrl = call.request().url().toString();
        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<List<PastHotelItem>>() {
            @Override
            public void onResponse(Call<List<PastHotelItem>> call, Response<List<PastHotelItem>> response) {
                if (!response.isSuccessful()) {
                    Log.d("response error", String.valueOf(response.code()));
                    return;
                }
                if (response.body() == null) {
                    Log.d("response error", "Empty response");
                    return;
                }
                ArrayList<PastHotelItem> searchHotelItems = (ArrayList<PastHotelItem>) response.body();
                if (searchHotelItems.size() == 0) {
                    Toast.makeText(getContext(), "NO SEARCH FOUND", Toast.LENGTH_LONG).show();
                    return;
                }
                cardHotelPastTripAdapter = new CardHotelPastTripAdapter(getContext(), searchHotelItems);
                recyclerView.setAdapter(cardHotelPastTripAdapter);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<PastHotelItem>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
                Log.d("loadHotel",t.toString());
            }
        });
    }

}