package com.example.mobdev_nhom7.fragment.main_activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev_nhom7.activity.ViewCity;
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.models.hotel.HotelItem;
import com.example.mobdev_nhom7.models.responseObj.places.CustomAdapter;
import com.example.mobdev_nhom7.utils.PlaceType;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityItemCardAdapter;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityItem;
import com.example.mobdev_nhom7.models.responseObj.places.PlaceItem;
import com.example.mobdev_nhom7.models.responseObj.places.PlaceItemCardAdapter;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.example.mobdev_nhom7.utils.SendID;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class SuggestedDestinationActivity extends Activity {
    public interface DataLoadedCallback {
        void onDataLoaded();
    }
    private final APIService apiService = APIUtils.getUserService();
    SendID sendID;
    RecyclerView recyclerView;

    NestedScrollView nestedScrollView;
    EditText editPreferredDest;
    ImageView imageBackButton;
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
        setContentView(R.layout.suggested_destination);
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
        recyclerView.setAdapter(cityItemCardAdapter);

        getSuggestDest();

        getAllHotel(() -> {
            getAllCity(() -> {
                adapter = new CustomAdapter(getApplicationContext(), placeItemList, new SendID() {
                    @Override
                    public void go(String hotel_id, String city_id) {
                        if (hotel_id != null && !hotel_id.equals("")) {
                            Intent intent = new Intent(getApplicationContext(), ViewHotel.class);
                            intent.putExtra("hotel_id", hotel_id);
                            startActivity(intent);
                        }
                        else if (city_id!= null && !city_id.equals("")) {
                            Intent intent = new Intent(getApplicationContext(), ViewCity.class);
                            intent.putExtra("city_id", city_id);
                            startActivity(intent);
                        }

                    }
                });
                Log.d("PlaceItem", String.valueOf(placeItemList.size()));
            });
        });

        imageBackButton.setOnClickListener(view -> finish());
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
                    recyclerView.setAdapter(cityItemCardAdapter);
                }
                else {
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
                         public void go(String hotel_id, String city_id) {
                             Intent intent = new Intent(getApplicationContext(), ViewCity.class);
                             intent.putExtra("city_id", city_id);
                             startActivity(intent);
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
    private void getAllCity(DataLoadedCallback callback) {
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
    private void getAllHotel(DataLoadedCallback callback) {
        Call<List<HotelItem>> call = apiService.getAllHotel();
        String requestUrl = call.request().url().toString();
        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<List<HotelItem>>() {
            @Override
            public void onResponse(Call<List<HotelItem>> call, Response<List<HotelItem>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                hotelItems = response.body();
                assert hotelItems != null;
                Log.d("hotelItemListResponse", hotelItems.toString());
                for (int i = 0; i < hotelItems.size(); i++) {

                    HotelItem hotelItem = hotelItems.get(i);
                    Log.d("hotelItem", hotelItem.getName());

                    PlaceItem placeItem = new PlaceItem();
                    placeItem.setName(hotelItem.getName());
                    placeItem.setHotel_id(hotelItem.getHotelId());
//                    if (hotelItem.getLocation() != null && hotelItem.getLocation().getAddress() != null) {
                    placeItem.setCountry(hotelItem.getCountry());
//                    }
                    placeItem.setType(PlaceType.HOTEL.getDisplayName());

                    placeItemList.add(placeItem);
                }
                callback.onDataLoaded();
            }

            @Override
            public void onFailure(Call<List<HotelItem>> call, Throwable t) {
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
        });

    }
}
