package com.example.mobdev_nhom7.models.responseObj.cityName;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.CityActivity;

import java.util.List;

public class CardCityNameAdapter extends RecyclerView.Adapter<CardCityNameAdapter.CityNameViewHolder> {

    private List<CityName> cityList;
    private Context context;

    // Constructor
    public CardCityNameAdapter(Context context, List<CityName> cityList) {
        this.context = context;
        this.cityList = cityList;
    }

    @Override
    public CityNameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_city_name, parent, false);
        return new CityNameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityNameViewHolder holder, int position) {
        CityName city = cityList.get(position);
        holder.bind(city);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CityActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    // View holder class
    public class CityNameViewHolder extends RecyclerView.ViewHolder {
        private TextView cityNameTextView;
        private TextView countryNameTextView;

        public CityNameViewHolder(View itemView) {
            super(itemView);
            cityNameTextView = itemView.findViewById(R.id.cityName);
            countryNameTextView = itemView.findViewById(R.id.countryName);
        }

        public void bind(CityName city) {
            cityNameTextView.setText("City ID: " + city.getCityId());
            countryNameTextView.setText("City Name: " + city.getCountry());
        }
    }
}
