package com.example.mobdev_nhom7.fragment.main_activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.models.responseObj.cityName.CardCityNameAdapter;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityName;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityNameResponseData;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuggestedDestinationActivity extends Activity {
    private APIService apiService = APIUtils.getUserService();
    String[] suggestedDest;

    RecyclerView recyclerView;
    NestedScrollView nestedScrollView;
    AutoCompleteTextView autoCompleteTextView;
    Button buttonCancel;
    //TODO: FROM ROUTING TO BE


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggested_destination);
        recyclerView = findViewById(R.id.recyclerView);
        autoCompleteTextView = findViewById(R.id.autoCompleteDest);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        buttonCancel = findViewById(R.id.buttonCancel);

        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<>(this,  R.layout.auto_complete_custom,R.id.recyclerView1, suggestedDest);
        autoCompleteTextView.setAdapter(arrayAdapter);
//        autoCompleteTextView.setOnClickListener(v-> {
//            if(autoCompleteTextView.getText().length()>= 1) {
//                nestedScrollView.setVisibility(View.GONE);
//
//            }
//            else {
//                nestedScrollView.setVisibility(View.VISIBLE);
//            }
//        });
        buttonCancel.setOnClickListener(v-> {
            autoCompleteTextView.setText("");
        });
        getSuggestDest();
     }
    private void getFilterPlacesList(){
        Call<CityNameResponseData> call = apiService.getAllCity();
        call.enqueue(new Callback<CityNameResponseData>() {
            @Override
            public void onResponse(@NonNull Call<CityNameResponseData> call, @NonNull Response<CityNameResponseData> response) {
                if (response.isSuccessful()) {
                    if  (response.body() == null) {
                        Log.d("Content", "Empty content");
                        Toast.makeText(getApplicationContext(), "Empty content", Toast.LENGTH_LONG).show();
                    }
                    ArrayList<CityName> cityNames = (ArrayList<CityName>) response.body().getData();
                    CardCityNameAdapter cardCityNameAdapter = new CardCityNameAdapter(getApplicationContext(), cityNames);
                    recyclerView.setAdapter(cardCityNameAdapter);
                }
            }

            @Override
            public void onFailure(Call<CityNameResponseData> call, Throwable t) {
                Log.d("call", t.toString());
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
     private void getSuggestDest() {
         Call<CityNameResponseData> call = apiService.getSuggestedCity();
         call.enqueue(new Callback<CityNameResponseData>() {
             @Override
             public void onResponse(@NonNull Call<CityNameResponseData> call, @NonNull Response<CityNameResponseData> response) {
                 if (response.isSuccessful()) {
                     if  (response.body() == null) {
                         Log.d("Content", "Empty content");
                         Toast.makeText(getApplicationContext(), "Empty content", Toast.LENGTH_LONG).show();
                     }
                     ArrayList<CityName> cityNames = (ArrayList<CityName>) response.body().getData();
                     CardCityNameAdapter cardCityNameAdapter = new CardCityNameAdapter(getApplicationContext(), cityNames);
                     recyclerView.setAdapter(cardCityNameAdapter);
                 }
             }

             @Override
             public void onFailure(Call<CityNameResponseData> call, Throwable t) {
                 Log.d("call", t.toString());
                 Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
             }
         });
     }
}
