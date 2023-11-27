package com.example.mobdev_nhom7.models.hotel.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.utils.AmountConverter;
import com.example.mobdev_nhom7.utils.BitmapUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CardHotelAdapter extends RecyclerView.Adapter<CardHotelAdapter.FavHotelViewHolder> {
    Context context;
    private List<SearchHotelItem> data;
    public SearchHotelItem getData(int x) {
        return data.get(x);
    }
    public CardHotelAdapter(Context context, ArrayList<SearchHotelItem > data) {
        this.context = context;
        this.data= data;
    }

    @NonNull
    @Override
    public FavHotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_hotel, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), ViewHotel.class);
                context.startActivity(intent);
            }
        });
        return new FavHotelViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FavHotelViewHolder holder, int position) {
        try {
            URL url = new URL(data.get(position).getImageItemURL().get(0));
            Log.d("url", url.toString());
            BitmapUtil.ggDriveConverter(url.toString(), holder.imagesHotel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        holder.textCity.setText(String.valueOf(data.get(position).getCity()));
        holder.textNumberofJudges.setText(String.valueOf(data.get(position).getScore().getCount() + " reviews"));
        holder.textScore.setText(String.valueOf(data.get(position).getScore().getValue()));
        holder.textRate.setText(AmountConverter.calculate(data.get(position).getScore().getValue()));
        holder.textHotelName.setText(String.valueOf(data.get(position).getName()));
        holder.ratingBar.setRating(data.get(position).getStar());
    }
    @Override
    public int getItemCount() {
        return data.size(); // Return the number of hotels in the list
    }

    public class FavHotelViewHolder extends RecyclerView.ViewHolder{
        TextView textCity, textScore, textHotelName, textRate, textNumberofJudges;
        RatingBar ratingBar;
        ImageView imagesHotel;
        public FavHotelViewHolder(@NonNull View itemView) {
            super(itemView);
            textCity = itemView.findViewById(R.id.textCity);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            textScore = itemView.findViewById(R.id.textScore);
            textHotelName = itemView.findViewById(R.id.textHotelName);
            textRate = itemView.findViewById(R.id.textRate);
            textNumberofJudges = itemView.findViewById(R.id.textNumberofJudges);
            imagesHotel = itemView.findViewById(R.id.imageView);
        }
    }

}
