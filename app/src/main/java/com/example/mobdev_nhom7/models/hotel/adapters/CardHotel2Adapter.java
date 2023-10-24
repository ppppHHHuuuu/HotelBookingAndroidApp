package com.example.mobdev_nhom7.models.hotel.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.ViewHotel;

import java.util.ArrayList;

public class CardHotel2Adapter extends RecyclerView.Adapter<CardHotel2Adapter.ListHotelViewHolder> {
    Context context;
    ArrayList hotels, stars, cities, scores, rates, judges, distance, lastBooking, amount;
    ArrayList<Integer> images; // Add images ArrayList
    RecyclerView recyclerView;

    public CardHotel2Adapter(Context context,
                             ArrayList<String> hotels,
                             ArrayList<String> stars,
                             ArrayList<String> cities,
                             ArrayList<String> scores,
                             ArrayList<String> rates,
                             ArrayList<String> judges,
                             ArrayList<Integer> images,
                             ArrayList<Double> distance,
                             ArrayList<String> lastBooking, ArrayList<Double> amount) {
        this.context = context;
        this.hotels = hotels;
        this.stars = stars;
        this.cities = cities;
        this.scores = scores;
        this.rates = rates;
        this.judges = judges;
        this.images = images;
        this.distance = distance;
        this.lastBooking = lastBooking;
        this.amount = amount;
    }



    @NonNull
    @Override
    public ListHotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_hotel_2, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), ViewHotel.class);
                Toast.makeText(context.getApplicationContext(), "Getting hotel details", Toast.LENGTH_LONG).show();
                context.startActivity(intent);
            }
        });
        return new ListHotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHotelViewHolder holder, int position) {
//        holder.textCity.setText(String.valueOf(cities.get(position)));
//        holder.tex.setText(String.valueOf(stars.get(position)));
        holder.textJudge.setText(String.valueOf(judges.get(position)));
        holder.textScore.setText(String.valueOf(scores.get(position)));
        holder.textJudge.setText(String.valueOf(rates.get(position)));
        holder.textHotelName.setText(String.valueOf(hotels.get(position)));
        holder.imagesHotel.setImageResource(images.get(position));
        holder.textAmount.setText(String.valueOf(amount.get(position)));
        holder.textDistance.setText(String.valueOf(distance.get(position)));
        holder.textLastBookingTime.setText(String.valueOf(lastBooking.get(position)));
    }

    @Override
    public int getItemCount() {
        return hotels.size(); // Return the number of hotels in the list
    }

    public class ListHotelViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagesHotel;
        private LinearLayout layoutIntroduce;
        private TextView textHotel;
        private TextView textHotelName;
        private TextView textScore;
        private TextView textJudge;
        private TextView textDistance;
        private TextView textAmount;
        private TextView textLastBookingTime;

        public ListHotelViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize variables with their corresponding itemViews
            imagesHotel = itemView.findViewById(R.id.imageHotel);
            layoutIntroduce = itemView.findViewById(R.id.layoutIntroduce);
            textHotel = itemView.findViewById(R.id.textHotel);
            textHotelName = itemView.findViewById(R.id.textHotelName);
            textScore = itemView.findViewById(R.id.textScore);
            textJudge = itemView.findViewById(R.id.textJudge);
            textDistance = itemView.findViewById(R.id.textDistance);
            textAmount = itemView.findViewById(R.id.textAmount);
            textLastBookingTime = itemView.findViewById(R.id.textLastBookingTime);

        }
    }
}
