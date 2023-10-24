package com.example.mobdev_nhom7.models.hotel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.models.hotel.HotelItem;

import java.util.ArrayList;

public class CardHotel2Adapter extends RecyclerView.Adapter<CardHotel2Adapter.ListHotelViewHolder> {
    Context context;
    ArrayList<HotelItem> hotels;

    public CardHotel2Adapter(Context context,
                             ArrayList<HotelItem> hotels) {
        this.context = context;
        this.hotels = hotels;
    }

    @NonNull
    @Override
    public ListHotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_hotel_2, parent, false);
        return new ListHotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHotelViewHolder holder, int position) {
        HotelItem hotelItem = hotels.get(position);
        holder.textJudge.setText(String.valueOf(hotelItem.getJudge()));
        holder.textScore.setText(String.valueOf(hotelItem.getScore()));
        holder.textJudge.setText(String.valueOf(hotelItem.getRate()));
        holder.textHotelName.setText(String.valueOf(hotelItem.getName()));
        holder.imagesHotel.setImageResource(hotelItem.getImage());
        holder.textAmount.setText(String.valueOf(hotelItem.getAmount()));
        holder.textDistance.setText(String.valueOf(hotelItem.getDistance()));
        holder.textLastBookingTime.setText(String.valueOf(hotelItem.getLastBooking()));
    }

    @Override
    public int getItemCount() {
        return hotels.size();
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
