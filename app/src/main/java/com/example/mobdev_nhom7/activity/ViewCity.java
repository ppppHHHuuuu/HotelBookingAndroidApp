package com.example.mobdev_nhom7.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        cityImage = findViewById(R.id.cityImage);
        String urls[] = {
                "https://firebasestorage.googleapis.com/v0/b/hotel-management-applica-2820c.appspot.com/o/hotel%2FRomeMarriottHotel.jpg?alt=media&token=e12b0a8e-b88a-46b4-8794-aa5df43d7742",
                "https://lh3.googleusercontent.com/u/0/drive-viewer/AK7aPaAdUoMDI1hncAN7nJF3wa4QSaAyLts3jyBu2Tc96Z2gbTuPdaWJ2HK0hRA0Sg-uEdz4CdtmWMOYYezle54tnMFe4eaU=w1920-h892",
                "https://firebasestorage.googleapis.com/v0/b/hotel-management-applica-2820c.appspot.com/o/hotel%2FSenatorBarajasMadrid.jpg?alt=media&token=b77757a3-44fc-41e6-bb1f-50d6cb09508b",
                "https://firebasestorage.googleapis.com/v0/b/hotel-management-applica-2820c.appspot.com/o/hotel%2FSheratonHaiPhong.jpg?alt=media&token=0f2059a7-d042-4911-bc76-b356a04f32f5",
                "https://firebasestorage.googleapis.com/v0/b/hotel-management-applica-2820c.appspot.com/o/hotel%2FMIAHotelHanoi.jpg?alt=media&token=dd691ee6-0e84-4421-a3b4-221ad2210a2e",
                "https://firebasestorage.googleapis.com/v0/b/hotel-management-applica-2820c.appspot.com/o/hotel%2FElianaPremioHanoiHotel.jpg?alt=media&token=2af3424f-cdf2-4470-9507-fa4d1acbecfe",
                "https://firebasestorage.googleapis.com/v0/b/hotel-management-applica-2820c.appspot.com/o/hotel%2FCochinSangHotel.jpg?alt=media&token=75f4ee39-4805-4832-b1d5-ac7019b0b9fc",
                "https://firebasestorage.googleapis.com/v0/b/hotel-management-applica-2820c.appspot.com/o/hotel%2FJWMarriottHanoi.jpg?alt=media&token=ba40d2d3-458d-43ab-ad9f-83e29e8b3d73",
                "https://firebasestorage.googleapis.com/v0/b/hotel-management-applica-2820c.appspot.com/o/hotel%2FManolitaParis.jpg?alt=media&token=ae2501b2-03c7-4425-8533-454543bffcb4",
                "https://firebasestorage.googleapis.com/v0/b/hotel-management-applica-2820c.appspot.com/o/hotel%2FParkCentralNewYork.jpg?alt=media&token=9e770e36-6650-4015-ba3a-2cdc02ec7432"
        };
        int randomIndex = new Random().nextInt(urls.length);
        String url = urls[randomIndex];
        Glide.with(this).load(url).centerCrop().into(cityImage);

        restaurants = new ArrayList<>();
        cardRestaurantAdapter = new CardRestaurantAdapter(getApplicationContext(), (ArrayList<Restaurant>) restaurants);
        restaurantsRecyclerView = findViewById(R.id.restaurantsRV);
        restaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        restaurantsRecyclerView.setAdapter(cardRestaurantAdapter);

        transportations = new ArrayList<>();
        cardTransportationAdapter = new CardTransportationAdapter(getApplicationContext(), (ArrayList<Transportation>) transportations);
        transportationsRecyclerView = findViewById(R.id.transportRV);
        transportationsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        transportationsRecyclerView.setAdapter(cardTransportationAdapter);

        todos = new ArrayList<>();
        cardTodoAdapter = new CardTodoAdapter(getApplicationContext(), (ArrayList<Todo>) todos);
        todosRecyclerView = findViewById(R.id.activitiesRV);
        todosRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        todosRecyclerView.setAdapter(cardTodoAdapter);
        String cityID;
        Bundle extras = this.getIntent().getExtras();
        if(extras == null) {
            Log.d("extra", "abc");
            cityID= null;
        } else {
            for (String key : extras.keySet()) {
                Log.e("extras", key + " : " + (extras.get(key) != null ? extras.get(key) : "NULL"));
            }
            if (extras.getString("city_id") != null) {
                cityID= extras.getString("city_id");
                getCityDetail(cityID);
                Log.d("city_id", cityID);
            }
            TextView cityName = findViewById(R.id.city_name);
            cityName.setText(extras.getString("city_name"));
            TextView cityCountry = findViewById(R.id.city_country);
            cityCountry.setText(extras.getString("city_country"));
        }
    }

    private void getCityDetail(String id) {
        Call<List<Restaurant>> callGetRestaurant = apiService.getRestaurant(id);
        String requestUrl = callGetRestaurant.request().url().toString();
        Log.d("Request URL", requestUrl);
        callGetRestaurant.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Handle the first CityDetail in the list
                    if (response.body().size() == 0) {
                        return;
                    }
                    restaurants.clear();
                    restaurants.addAll(response.body());
                    cardRestaurantAdapter.notifyDataSetChanged();
                } else {
                    Log.d("Call get restaurant error", "Empty or unsuccessful response");
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Log.d("Call get restaurant error", t.getMessage());
            }
        });

        Call<List<Transportation>> callGetTransportation = apiService.getTransportation(id);
        String requestUrl1 = callGetTransportation.request().url().toString();
        Log.d("Request URL", requestUrl1);
        callGetTransportation.enqueue(new Callback<List<Transportation>>() {
            @Override
            public void onResponse(Call<List<Transportation>> call, Response<List<Transportation>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Handle the first CityDetail in the list
                    if (response.body().size() == 0) {
                        Toast.makeText(getApplicationContext(), "NO SEARCH FOUND", Toast.LENGTH_LONG).show();
                        return;
                    }
                    transportations.clear();
                    transportations.addAll(response.body());
                    cardTransportationAdapter.notifyDataSetChanged();
                } else {
                    Log.d("Call get city error", "Empty or unsuccessful response");
                }
            }

            @Override
            public void onFailure(Call<List<Transportation>> call, Throwable t) {
                Log.d("Call get city error", t.getMessage());
            }
        });

        Call<List<Todo>> callGetTodo = apiService.getTodo(id);
        callGetTodo.enqueue(new Callback<List<Todo>>() {
            @Override
            public void onResponse(Call<List<Todo>> call, Response<List<Todo>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Handle the first CityDetail in the list
                    todos.clear();
                    todos.addAll(response.body());
                    cardTodoAdapter.notifyDataSetChanged();
                } else {
                    Log.d("Call get city error", "Empty or unsuccessful response");
                }
            }

            @Override
            public void onFailure(Call<List<Todo>> call, Throwable t) {
                Log.d("Call get city error", t.getMessage());
            }
        });

        Call<List<Alert>> callGetAlert = apiService.getAlert(id);
        callGetAlert.enqueue(new Callback<List<Alert>>() {
            @Override
            public void onResponse(Call<List<Alert>> call, Response<List<Alert>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Handle the first CityDetail in the list
                    alerts = response.body();
                    TextView alertContent = findViewById(R.id.alert_content);
                    String a = "";
                    for (int i = 0; i < alerts.size(); i++) {
                        if (i > 0) {
                            a = a + alerts.get(i).getContent();
                        } else {
                            a = a + alerts.get(i).getContent() + "\n";
                        }

                    }
                    alertContent.setText(a);
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