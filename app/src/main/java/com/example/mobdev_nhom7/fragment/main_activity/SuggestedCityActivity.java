package com.example.mobdev_nhom7.fragment.main_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.ViewCity;
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.models.hotel.HotelItem;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityItem;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityItemCardAdapter;
import com.example.mobdev_nhom7.models.responseObj.places.CustomAdapter;
import com.example.mobdev_nhom7.models.responseObj.places.PlaceItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.example.mobdev_nhom7.utils.PlaceType;
import com.example.mobdev_nhom7.utils.SendID;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class SuggestedCityActivity extends AppCompatActivity {
    public interface DataLoadedCallback {
        void onDataLoaded();
    }

    private final APIService apiService = APIUtils.getUserService();
    SendID sendID;
    RecyclerView recyclerView;
    SharedPreferences preferencesEdittext;

    NestedScrollView nestedScrollView;
    EditText editPreferredDest;
    ImageView imageBackButton;
    ImageView buttonKinhLup;
    TextView suggestedPlace;
    ImageView buttonCancel;
    List<String> data = new ArrayList<>();
    List<PlaceItem> placeItemList;
    List<CityItem> cityItems;
    List<HotelItem> hotelItems;

    CityItemCardAdapter cityItemCardAdapter;
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggested_city);

        hotelItems = new ArrayList<>();
        cityItems = new ArrayList<>();
        imageBackButton = findViewById(R.id.imageBackButton);
        suggestedPlace = findViewById(R.id.suggestedPlace);
        editPreferredDest = findViewById(R.id.editPreferredDest);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        placeItemList = new ArrayList<>();
        buttonCancel = findViewById(R.id.buttonCancel);

        buttonCancel.setOnClickListener(v-> {
            editPreferredDest.setText("");
        });

        imageBackButton.setOnClickListener(v -> {
            onBackPressed();
        });

        recyclerView.setAdapter(cityItemCardAdapter);
        preferencesEdittext = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);

        buttonKinhLup = findViewById(R.id.buttonKinhLup);
        buttonKinhLup.setVisibility(View.INVISIBLE);
        buttonKinhLup.setOnClickListener(v -> {
            String editTextContent = editPreferredDest.getText().toString();

            // Store the data you want to pass to MainActivity in a shared preference, for example
            SharedPreferences preferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("destination", editTextContent);
            editor.putBoolean("search", true);
            editor.apply();

            // Go back to MainActivity
            onBackPressed();
        });
        getSuggestDest();

        getAllCity(() -> {
            adapter = new CustomAdapter(getApplicationContext(), placeItemList, new SendID() {
                @Override
                public void go(String hotel_id, String city_id, String reservation_id) {
                    if (city_id!= null && !city_id.equals("")) {
                        Intent intent;
                        intent = new Intent(getApplicationContext(), ViewCity.class);
                        for (int i = 0; i < cityItems.size(); i++) {
                            if (cityItems.get(i).getCityId() == city_id) {
                                intent.putExtra("city_name", cityItems.get(i).getCityName());
                                break;
                            }
                        }
                        if (hotel_id == null) {
                            intent.putExtra("city_id", city_id);
                            startActivity(intent);
                        }
                    }
                }
            });
            Log.d("PlaceItem", String.valueOf(placeItemList.size()));
        });
        editPreferredDest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //this check with the editable
                String searchText = editable.toString().trim();
                if (searchText.isEmpty()) {
                    buttonKinhLup.setVisibility(View.INVISIBLE);

                    recyclerView.setAdapter(cityItemCardAdapter);
                }
                else {
                    buttonKinhLup.setVisibility(View.VISIBLE);

                    try {
                        adapter.getFilter().filter(searchText);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                    }
                    catch (Exception e) {
                        Log.d("exception", e.getMessage().toString());
                    }
                }
            }
        });
        imageBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
                String editTextContent = editPreferredDest.getText().toString();

                // Store the data you want to pass to MainActivity in a shared preference, for example
                SharedPreferences preferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("destination", editTextContent);
                editor.putBoolean("search", false);

                editor.apply();

                // Go back to MainActivity
                onBackPressed();
            }
        });
    }

    public void getSuggestDest() {
        Call<List<CityItem>> call = apiService.getSuggestedCity();
        call.enqueue(new Callback<List<CityItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<CityItem>> call, @NonNull Response<List<CityItem>> response) {
                if (response.isSuccessful()) {
                    if  (response.body() == null) {
                        Log.d("Content", "Empty content");
                        Toast.makeText(getApplicationContext(), "Empty content", Toast.LENGTH_LONG).show();
                    }
                    cityItems = (ArrayList<CityItem>) response.body();
                    for (int i = 0; i < response.body().size(); i++ ) {
                        Log.d("cityItems", response.body().get(i).getCityName());
                    }
                    cityItemCardAdapter = new CityItemCardAdapter(getApplicationContext(), cityItems, new SendID() {

                        @Override
                        public void go(String hotel_id, String city_id, String reservation_id) {
                             Intent intent;
                             intent = new Intent(getApplicationContext(), ViewCity.class);
                             for (int i = 0; i < cityItems.size(); i++) {
                                 if (Objects.equals(cityItems.get(i).getCityId(), city_id)) {
                                     intent.putExtra("city_country", cityItems.get(i).getCountry());
                                     intent.putExtra("city_name", cityItems.get(i).getCityName());
                                     break;
                                 }
                             }

                             if (hotel_id == null) {
                                 intent.putExtra("city_id", city_id);
                                 startActivity(intent);
                             }
                        }
                    });
                    recyclerView.setAdapter(cityItemCardAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<CityItem>> call, Throwable t) {
                Log.d("call", t.toString());
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Map<String, ?> allPreferences = preferencesEdittext.getAll();

        for (Map.Entry<String, ?> entry : allPreferences.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Log.d("SharedPreferences", "Key: " + key + ", Value: " + value);
        }
        String savedDestination = preferencesEdittext.getString("destination", "");
        editPreferredDest.setText(savedDestination);
    }

    private void getAllCity(SuggestedDestinationActivity.DataLoadedCallback callback) {
        Call<List<CityItem>> call = apiService.getAllCity();
        String requestUrl = call.request().url().toString();
        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<List<CityItem>>() {
                         @Override
                         public void onResponse(Call<List<CityItem>> call, Response<List<CityItem>> response) {
                             if (!response.isSuccessful()) {
                                 Toast.makeText(getApplicationContext(), String.valueOf(response.code()), Toast.LENGTH_LONG).show();
                                 return;
                             }
                             cityItems = response.body();
                             Log.d("cityItemListResponse", cityItems.toString());
                             for (int i = 0; i < cityItems.size(); i++) {
                                 CityItem cityItem = cityItems.get(i);
                                 PlaceItem placeItem = new PlaceItem();
                                 placeItem.setCity_id(cityItem.getCityId());
                                 placeItem.setName(cityItem.getCityName());
                                 placeItem.setCountry(cityItem.getCountry());
                                 placeItem.setType(PlaceType.CITY.getDisplayName());
                                 placeItemList.add(placeItem);
                             }
                             callback.onDataLoaded();
                         }

                         @Override
                         public void onFailure(Call<List<CityItem>> call, Throwable t) {
                             if (t instanceof IOException) {
                                 // An IOException occurred, usually a network issue
                                 Log.e("onFailure", "Network error: " + t.getMessage());
                                 Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
                             } else if (t instanceof HttpException) {
                                 // An HTTP exception occurred, get the status code
                                 HttpException httpException = (HttpException) t;
                                 int statusCode = httpException.code();
                                 String errorBody = httpException.response().errorBody().toString();
                                 Log.e("onFailure", "HTTP error: " + statusCode + ", " + errorBody);
                                 Toast.makeText(getApplicationContext(), "HTTP error: " + statusCode, Toast.LENGTH_SHORT).show();
                             } else {
                                 // Other types of exceptions
                                 Log.e("onFailure", "Error: " + t.getMessage());
                                 Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                             }
                         }
                     }
        );
    }
}