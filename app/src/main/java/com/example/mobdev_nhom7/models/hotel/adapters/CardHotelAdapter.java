package com.example.mobdev_nhom7.models.hotel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.models.hotel.HotelItem;

import java.util.ArrayList;

public class CardHotelAdapter extends RecyclerView.Adapter<CardHotelAdapter.FavHotelViewHolder> {
    Context context;
    ArrayList<HotelItem> hotels;

    public CardHotelAdapter(Context context,
                            ArrayList<HotelItem> hotels) {
        this.context = context;
        this.hotels = hotels;
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
        HotelItem hotelItem = hotels.get(position);
        holder.textCity.setText(String.valueOf(hotelItem.getCity()));
        holder.textStarNumber.setText(String.valueOf(hotelItem.getStar()));
        holder.textNumberofJudges.setText(String.valueOf(hotelItem.getJudge()));
        holder.textScore.setText(String.valueOf(hotelItem.getScore()));
        holder.textRate.setText(String.valueOf(hotelItem.getRate()));
        holder.textHotelName.setText(String.valueOf(hotelItem.getName()));
        holder.imagesHotel.setImageResource(hotelItem.getImage());
    }

    @Override
    public int getItemCount() {
        return hotels.size();
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
