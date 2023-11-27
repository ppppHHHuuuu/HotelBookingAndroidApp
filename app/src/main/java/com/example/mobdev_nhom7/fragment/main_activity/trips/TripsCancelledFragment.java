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
import com.example.mobdev_nhom7.models.responseObj.trips.adapters.CardHotelCancelledTripAdapter;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripsCancelledFragment extends Fragment {
    private APIService apiService = APIUtils.getUserService();
    SharedPreferences preferences;

    CardHotelCancelledTripAdapter cardHotelCancelledTripAdapter;
    ArrayList<CancelledHotelItem> hotelItemList = new ArrayList<>();

    RecyclerView recyclerView;

    public TripsCancelledFragment() {
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

        cardHotelCancelledTripAdapter = new CardHotelCancelledTripAdapter(getContext(), hotelItemList);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(cardHotelCancelledTripAdapter);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserCancelHotel();

    }

    private void getUserCancelHotel() {
        String dummyUserID = "1";
        String user_id = preferences.getString("user_id", "empty user_id");
        Log.d("user_id", user_id);

        Call<List<CancelledHotelItem>> call = apiService.getCancelReservation(dummyUserID);
        String requestUrl = call.request().url().toString();
        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<List<CancelledHotelItem>>() {
            @Override
            public void onResponse(Call<List<CancelledHotelItem>> call, Response<List<CancelledHotelItem>> response) {
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
                cardHotelCancelledTripAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CancelledHotelItem>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
                Log.d("loadHotel",t.toString());
            }
        });
    }
}