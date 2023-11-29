package com.example.mobdev_nhom7.fragment.main_activity.trips;

import android.content.Intent;
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
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.models.responseObj.DefaultResponseObj;
import com.example.mobdev_nhom7.models.responseObj.ratings.RatingItem;
import com.example.mobdev_nhom7.models.responseObj.trips.PastHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.adapters.CardHotelPastTripAdapter;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.example.mobdev_nhom7.utils.SendID;

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
    ArrayList<PastHotelItem> hotelItemList;


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
        View v = inflater.inflate(R.layout.fragment_trips_past, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        hotelItemList = new ArrayList<>();
        cardHotelPastTripAdapter = new CardHotelPastTripAdapter(getContext(), hotelItemList, new SendID() {
            @Override
            public void go(String hotel_id, String city_id, String reservation_id) {
                Intent intent = new Intent(getContext(), ViewHotel.class);
//                intent.putExtra("reservation_id", reservation_id);
                intent.putExtra("hotel_id", hotel_id);
                startActivity(intent);
            }
        });
        recyclerView = (RecyclerView) v.findViewById(R.id.recycleView);
        recyclerView.setAdapter(cardHotelPastTripAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserNotRatedPastHotel();
    }

    //TODO CALL FROM BE
    private void getUserRatedPastHotel() {
        //fake-data

        String user_id = preferences.getString("user_id", "empty user_id");
        Log.d("user_id", user_id);

        Call<List<PastHotelItem>> call = apiService.getRatedReservation(user_id);
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
                hotelItemList.addAll(response.body());
                cardHotelPastTripAdapter.notifyDataSetChanged();
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

        Call<List<PastHotelItem>> call = apiService.getNotRatedReservation(user_id);
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

                hotelItemList.clear();
                hotelItemList.addAll(response.body());
                getUserRatedPastHotel();
                cardHotelPastTripAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PastHotelItem>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
                Log.d("loadHotel",t.toString());
            }
        });
    }
    private void postUserCommentHotel(String comment, RatingItem rating) {
        String reservationID = preferences.getString("reservation_id", "empty reservation_id");
        Call<DefaultResponseObj> call = apiService.postUserCommentHotel(reservationID, rating, comment);
        String requestUrl = call.request().url().toString();
        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<DefaultResponseObj>() {
            @Override
            public void onResponse(Call<DefaultResponseObj> call, Response<DefaultResponseObj> response) {
                if (!response.isSuccessful()) {
                    Log.d("response error", String.valueOf(response.code()));
                    return;
                }
                if (response.body() == null) {
                    Log.d("response error", "Empty response");
                    return;
                }
            }

            @Override
            public void onFailure(Call<DefaultResponseObj> call, Throwable t) {
                Toast.makeText(getContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
                Log.d("loadHotel",t.toString());
            }
        });
    }

}