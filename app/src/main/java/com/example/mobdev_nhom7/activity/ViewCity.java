package com.example.mobdev_nhom7.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Alert;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Restaurant;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Todo;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Transportation;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.adapters.CardRestaurantAdapter;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.adapters.CardTodoAdapter;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.adapters.CardTransportationAdapter;
import com.example.mobdev_nhom7.models.responseObj.trips.adapters.CardHotelActiveTripAdapter;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCity extends AppCompatActivity {
    private RecyclerView restaurantsRecyclerView;
    private RecyclerView transportationsRecyclerView;
    private RecyclerView todosRecyclerView;
    private ImageView cityImage;
    private List<Restaurant> restaurants;
    private List<Todo> todos;
    private List<Transportation> transportations;
    private List<Alert> alerts;
    private APIService apiService = APIUtils.getUserService();
    private CardRestaurantAdapter cardRestaurantAdapter;
    private CardTodoAdapter cardTodoAdapter;
    private CardTransportationAdapter cardTransportationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_detail);
        getCityDetail("nw2udhrsvdQGsXSgOO43");

        cityImage = findViewById(R.id.cityImage);
        String url = "https://lh3.googleusercontent.com/u/0/drive-viewer/AK7aPaAdUoMDI1hncAN7nJF3wa4QSaAyLts3jyBu2Tc96Z2gbTuPdaWJ2HK0hRA0Sg-uEdz4CdtmWMOYYezle54tnMFe4eaU=w1920-h892";
        Glide.with(this).load(url).centerCrop().into(cityImage);

        restaurantsRecyclerView = findViewById(R.id.restaurantsRV);
        restaurantsRecyclerView.setAdapter(cardRestaurantAdapter);
        restaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        transportationsRecyclerView = findViewById(R.id.transportRV);
        transportationsRecyclerView.setAdapter(cardTransportationAdapter);
        transportationsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        todosRecyclerView = findViewById(R.id.activitiesRV);
        todosRecyclerView.setAdapter(cardTodoAdapter);
        todosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getCityDetail(String id) {
        Call<List<Restaurant>> callGetRestaurant = apiService.getRestaurant("nw2udhrsvdQGsXSgOO43");
        callGetRestaurant.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Handle the first CityDetail in the list
                    restaurants = response.body();
                    cardRestaurantAdapter = new CardRestaurantAdapter(getApplicationContext(), (ArrayList<Restaurant>) restaurants);
                    restaurantsRecyclerView.setAdapter(cardRestaurantAdapter);
                    restaurantsRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    Log.d("Call get city error", "Empty or unsuccessful response");
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Log.d("Call get city error", t.getMessage());
            }
        });

        Call<List<Transportation>> callGetTransportation = apiService.getTransportation("nw2udhrsvdQGsXSgOO43");
        callGetTransportation.enqueue(new Callback<List<Transportation>>() {
            @Override
            public void onResponse(Call<List<Transportation>> call, Response<List<Transportation>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Handle the first CityDetail in the list
                    transportations = response.body();
                    cardTransportationAdapter = new CardTransportationAdapter(getApplicationContext(), (ArrayList<Transportation>) transportations);
                    transportationsRecyclerView.setAdapter(cardTransportationAdapter);
                    transportationsRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    Log.d("Call get city error", "Empty or unsuccessful response");
                }
            }

            @Override
            public void onFailure(Call<List<Transportation>> call, Throwable t) {
                Log.d("Call get city error", t.getMessage());
            }
        });

        Call<List<Todo>> callGetTodo = apiService.getTodo("nw2udhrsvdQGsXSgOO43");
        callGetTodo.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Handle the first CityDetail in the list
                    todos = response.body();
                    cardTodoAdapter = new CardTodoAdapter(getApplicationContext(), (ArrayList<Todo>) todos);
                    todosRecyclerView.setAdapter(cardTodoAdapter);
                    todosRecyclerView.setVisibility(View.VISIBLE);
                } else {
                    Log.d("Call get city error", "Empty or unsuccessful response");
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.d("Call get city error", t.getMessage());
            }
        });

        Call<List<Alert>> callGetAlert = apiService.getAlert("nw2udhrsvdQGsXSgOO43");
        callGetAlert.enqueue(new Callback<List<Alert>>() {
            @Override
            public void onResponse(Call<List<Alert>> call, Response<List<Alert>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Handle the first CityDetail in the list
                    alerts = response.body();
                } else {
                    Log.d("Call get city error", "Empty or unsuccessful response");
                }
            }

            @Override
            public void onFailure(Call<List<Alert>> call, Throwable t) {
                Log.d("Call get city error", t.getMessage());
            }
        });
    }
}