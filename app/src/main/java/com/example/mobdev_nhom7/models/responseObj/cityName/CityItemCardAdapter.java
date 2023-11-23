package com.example.mobdev_nhom7.models.responseObj.cityName;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.CityActivity;

import java.util.List;

public class CityItemCardAdapter extends RecyclerView.Adapter<CityItemCardAdapter.CityItemViewHolder> {

    private List<CityItem> cityList;
    private Context context;

    // Constructor
    public CityItemCardAdapter(Context context, List<CityItem> cityList) {
        this.context = context;
        this.cityList = cityList;
        setHasStableIds(true);

    }

    @Override
    public CityItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_city_name, parent, false);
        return new CityItemViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        CityItem cityItem = cityList.get(position);
        return cityItem.hashCode();
    }
    
    @Override
    public void onBindViewHolder(CityItemViewHolder holder, int position) {
        CityItem city = cityList.get(position);
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
    public class CityItemViewHolder extends RecyclerView.ViewHolder {
        private TextView cityNameTextView;
        private TextView countryNameTextView;

        public CityItemViewHolder(View itemView) {
            super(itemView);
            cityNameTextView = itemView.findViewById(R.id.cityName);
            countryNameTextView = itemView.findViewById(R.id.countryName);
        }

        public void bind(CityItem city) {
            cityNameTextView.setText(city.getCityName());
            countryNameTextView.setText(city.getCountry());
        }
    }
}
