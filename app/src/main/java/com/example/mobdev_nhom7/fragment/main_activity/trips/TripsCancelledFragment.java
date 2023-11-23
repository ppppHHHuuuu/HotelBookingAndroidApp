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
import com.example.mobdev_nhom7.models.hotel.adapters.CardHotelAdapter;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.HistoryHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.adapters.CardHotelActiveTripAdapter;
import com.example.mobdev_nhom7.models.responseObj.trips.adapters.CardHotelCancelledTripAdapter;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.example.mobdev_nhom7.utils.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TripsCancelledFragment extends Fragment {
    private APIService apiService = APIUtils.getUserService();
    SharedPreferences preferences;

    CardHotelCancelledTripAdapter cardHotelCancelledTripAdapter;
    ArrayList<HistoryHotelItem> hotelItemList = new ArrayList<>();

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

        recyclerView.setAdapter(cardHotelCancelledTripAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        getUserCancelHotel();

        return v;
    }

    private void getUserCancelHotel() {
        String user_id = preferences.getString("user_id", "empty user_id");
        Log.d("user_id", user_id);

        Call<List<HistoryHotelItem>> call = apiService.getHistoryReservation(user_id);
        call.enqueue(new Callback<List<HistoryHotelItem>>() {
            @Override
            public void onResponse(Call<List<HistoryHotelItem>> call, Response<List<HistoryHotelItem>> response) {
                if (!response.isSuccessful()) {
                    Log.d("response error", String.valueOf(response.code()));
                    return;
                }
                if (response.body() == null) {
                    Log.d("response error", "Empty response");
                    return;
                }
                ArrayList<HistoryHotelItem> searchHotelItems = (ArrayList<HistoryHotelItem>) response.body();
                ArrayList<HistoryHotelItem> cancelHotelItems = new ArrayList<>();
                if (searchHotelItems.size() == 0) {
                    Toast.makeText(getContext(), "NO SEARCH FOUND", Toast.LENGTH_LONG).show();
                    //ADD LOADING QUERY
                    return;
                }

                for (int i = 0; i < searchHotelItems.size(); i++) {
                    Log.d("is cancelled", searchHotelItems.get(i).getIsCancelled().toString());
                    Log.d("end date", searchHotelItems.get(i).getEndDate());
                    String date = searchHotelItems.get(i).getEndDate();
                    if (searchHotelItems.get(i).getIsCancelled()) {
                        cancelHotelItems.add(searchHotelItems.get(i));
                    }
                    else if (DateTimeUtil.isBeforeCurrentDate(date)) {
                        continue;
                    }
                    else {
                        continue;
                    }
                }
                cardHotelCancelledTripAdapter = new CardHotelCancelledTripAdapter(getContext(), cancelHotelItems);
                recyclerView.setAdapter(cardHotelCancelledTripAdapter);
                recyclerView.setVisibility(View.VISIBLE);
                Log.e("APIError", "Error code: " + response.code() + ", Message: " + response.message());
            }

            @Override
            public void onFailure(Call<List<HistoryHotelItem>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
                Log.d("loadHotel",t.toString());
            }
        });
    }


}