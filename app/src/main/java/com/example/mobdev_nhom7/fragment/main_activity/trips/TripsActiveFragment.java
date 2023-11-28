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
    ArrayList<ActiveHotelItem> hotelItemList;
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

        hotelItemList = new ArrayList<>();
        cardHotelActiveTripAdapter = new CardHotelActiveTripAdapter(requireContext(), hotelItemList);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cardHotelActiveTripAdapter);

        return v;
    }
    //TODO CALL FROM BE


    @Override
    public void onResume() {
        super.onResume();
        getUserActiveHotel();
    }

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

                if (response.body().size() == 0) {
                    Toast.makeText(getContext(), "NO SEARCH FOUND", Toast.LENGTH_LONG).show();
                    return;
                }
                hotelItemList.clear();
                hotelItemList.addAll(response.body());
                cardHotelActiveTripAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ActiveHotelItem>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
                Log.d("loadHotel",t.toString());
            }
        });
    }


}