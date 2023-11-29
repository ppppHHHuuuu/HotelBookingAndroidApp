package com.example.mobdev_nhom7.fragment.main_activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.ViewCity;
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.databinding.CardCityBinding;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Alert;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Restaurant;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Todo;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Transportation;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityFragment extends Fragment {
    private APIService apiService = APIUtils.getUserService();
    CardCityBinding cardCityBinding;
    RecyclerView recyclerView;

    // Dump Attribute, for demo only
    private Button cityDetail;
    private EditText citySearchBar;
    private List<CityItem> cityItems;

    public CityFragment() {
        // Required empty public constructor
    }

    public static CityFragment newInstance(String param1, String param2) {
        CityFragment fragment = new CityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getCityName();
        View view =  inflater.inflate(R.layout.fragment_city, container, false);
        cityDetail = view.findViewById(R.id.button4);
        citySearchBar = view.findViewById(R.id.searchbar);

        cityDetail.setOnClickListener(view1 -> {
            Intent intent = new Intent(getContext(), ViewCity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Add this line
            getContext().startActivity(intent);
        });

        citySearchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEditTextContent();
                getSuggestedDestinationActivity();
            }
        });

        return view;
    }

    public void getCityName() {
        Call<List<CityItem>> callGetAlert = apiService.getAllCity();
        callGetAlert.enqueue(new Callback<List<CityItem>>() {
            @Override
            public void onResponse(Call<List<CityItem>> call, Response<List<CityItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Handle the first CityDetail in the list
                    cityItems = response.body();
                } else {
                    Log.d("Call get city error", "Empty or unsuccessful response");
                }
            }

            @Override
            public void onFailure(Call<List<CityItem>> call, Throwable t) {
                Log.d("Call get city error", t.getMessage());
            }
        });
    }

    private void saveEditTextContent() {
        String editTextContent = citySearchBar.getText().toString();

        // Use SharedPreferences to save the content
        SharedPreferences preferences = getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("destination", editTextContent);
        editor.apply();
    }
    public void getSuggestedDestinationActivity() {
        Intent intent = new Intent(getContext(), SuggestedDestinationActivity.class);
        startActivity(intent);
    }
}