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
import com.example.mobdev_nhom7.models.hotel.adapters.CardHotel2Adapter;
import com.example.mobdev_nhom7.models.hotel.adapters.CardHotelAdapter;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.ActiveHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.ActiveHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.adapters.CardHotelActiveTripAdapter;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.example.mobdev_nhom7.utils.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripsActiveFragment extends Fragment {
    private APIService apiService = APIUtils.getUserService();
    SharedPreferences preferences;

    CardHotelActiveTripAdapter cardHotelActiveTripAdapter;
    ArrayList<ActiveHotelItem> hotelItemList = new ArrayList<>();
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

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        recyclerView = (RecyclerView) v.findViewById(R.id.recycleView);

        recyclerView.setAdapter(cardHotelActiveTripAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getUserActiveHotel();

        return v;
    }
    //TODO CALL FROM BE

    private void getUserActiveHotel() {
        //fake-data
        String user_id = preferences.getString("user_id", "empty user_id");
        Log.d("user_id", user_id);
        String dummyUserID = "1";
        Call<List<ActiveHotelItem>> call = apiService.getActiveReservation(dummyUserID);
        String requestUrl = call.request().url().toString();
        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<List<ActiveHotelItem>>() {
            @Override
            public void onResponse(Call<List<ActiveHotelItem>> call, Response<List<ActiveHotelItem>> response) {
                if (!response.isSuccessful()) {
                    Log.d("response error", String.valueOf(response.code()));
                    return;
                }
                if (response.body() == null) {
                    Log.d("response error", "Empty response");
                    return;
                }
                ArrayList<ActiveHotelItem> searchHotelItems = (ArrayList<ActiveHotelItem>) response.body();
                for (int i = 0; i < searchHotelItems.size(); i++) {
                    Log.d("searchHotelItem", searchHotelItems.get(i).toString());
                }
                if (searchHotelItems.size() == 0) {
                    Toast.makeText(getContext(), "NO SEARCH FOUND", Toast.LENGTH_LONG).show();
                    return;
                }
                cardHotelActiveTripAdapter = new CardHotelActiveTripAdapter(getContext(), searchHotelItems);
                recyclerView.setAdapter(cardHotelActiveTripAdapter);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<ActiveHotelItem>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
                Log.d("loadHotel",t.toString());
            }
        });
    }


}