package com.example.mobdev_nhom7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;

import java.util.ArrayList;

public class CardHotelAdapter extends RecyclerView.Adapter<CardHotelAdapter.FavHotelViewHolder> {
    Context context;
    ArrayList hotels, stars, cities, scores, rates, judges;
    ArrayList<Integer> images; // Add images ArrayList

    CardHotelAdapter(Context context, ArrayList<String> hotels, ArrayList<String> stars, ArrayList<String> cities, ArrayList<String> scores, ArrayList<String> rates, ArrayList<String> judges, ArrayList<Integer> images) {
        this.context = context;
        this.hotels = hotels;
        this.stars = stars;
        this.cities = cities;
        this.scores = scores;
        this.rates = rates;
        this.judges = judges;
        this.images = images;
    }
    @NonNull
    @Override
    public FavHotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_hotel, parent, false);
        return new FavHotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavHotelViewHolder holder, int position) {
        holder.textCity.setText(String.valueOf(cities.get(position)));
        holder.textStarNumber.setText(String.valueOf(stars.get(position)));
        holder.textNumberofJudges.setText(String.valueOf(judges.get(position)));
        holder.textScore.setText(String.valueOf(scores.get(position)));
        holder.textRate.setText(String.valueOf(rates.get(position)));
        holder.textHotelName.setText(String.valueOf(hotels.get(position)));
        holder.imagesHotel.setImageResource(images.get(position));

    }

    @Override
    public int getItemCount() {
        return hotels.size(); // Return the number of hotels in the list
    }

    public class FavHotelViewHolder extends RecyclerView.ViewHolder{
        TextView textCity, textStarNumber, textScore, textHotelName, textRate, textNumberofJudges;
        ImageView imagesHotel;
        public FavHotelViewHolder(@NonNull View itemView) {
            super(itemView);
            textCity = itemView.findViewById(R.id.textCity);
            textStarNumber = itemView.findViewById(R.id.textStarNumber);
            textScore = itemView.findViewById(R.id.textScore);
            textHotelName = itemView.findViewById(R.id.textHotelName);
            textRate = itemView.findViewById(R.id.textRate);
            textNumberofJudges = itemView.findViewById(R.id.textNumberofJudges);
            imagesHotel = itemView.findViewById(R.id.imageView);
        }
    }
}
