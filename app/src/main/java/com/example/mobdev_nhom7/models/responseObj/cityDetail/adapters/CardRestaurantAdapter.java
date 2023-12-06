package com.example.mobdev_nhom7.models.responseObj.cityDetail.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.ViewCity;
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Restaurant;
import com.example.mobdev_nhom7.models.responseObj.trips.adapters.CardHotelActiveTripAdapter;
import com.example.mobdev_nhom7.utils.BitmapUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CardRestaurantAdapter extends RecyclerView.Adapter<CardRestaurantAdapter.ListRestaurantHolder> {
    Context context;
    private List<Restaurant> data;
    public Restaurant getData(int x) {
        return data.get(x);
    }
    public CardRestaurantAdapter(Context context, ArrayList<Restaurant> data) {
        this.data= data;
        this.context = context;
    }

    @NonNull
    @Override
    public CardRestaurantAdapter.ListRestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_city_details, parent, false);

        return new CardRestaurantAdapter.ListRestaurantHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardRestaurantAdapter.ListRestaurantHolder holder, int position) {
        try {
            Log.d("asd", data.get(position).getImage());
            BitmapUtil.ggDriveConverter(data.get(position).getImage(), holder.imageRestaurant);
        }
        catch (Exception e) {
            Log.e("CardRestaurantAdapter", "Error loading image: " + e.getMessage(), e);
        }

        String name = data.get(position).getName();
        Float rating = (float) data.get(position).getRating()/2;
        String address = data.get(position).getAddress();

        holder.textName.setText(name);
        holder.textRating.setRating(rating);
        holder.textAddress.setText(address);
    }

    @Override
    public int getItemCount() {
        return data.size(); // Return the number of hotels in the list
    }
    public class ListRestaurantHolder extends RecyclerView.ViewHolder {
        private ImageView imageRestaurant = itemView.findViewById(R.id.image);
        private final TextView textName;
        private final RatingBar textRating;
        private final TextView textAddress;
        public ListRestaurantHolder(@NonNull View itemView) {
            super(itemView);
            imageRestaurant = itemView.findViewById(R.id.image);
            textName = itemView.findViewById(R.id.name);
            textRating = itemView.findViewById(R.id.rating);
            textAddress = itemView.findViewById(R.id.address);
        }
    }
}
