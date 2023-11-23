package com.example.mobdev_nhom7.models.hotel.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;

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
    public CardHotelAdapter(ArrayList<SearchHotelItem > data) {
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
        Bitmap hotelImage;
        try {
            URL url = new URL(data.get(position).getImageURL());
            hotelImage = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        holder.textCity.setText(String.valueOf(data.get(position).getCity()));
        holder.textStarNumber.setText(String.valueOf(data.get(position).getStar()));
        holder.textNumberofJudges.setText(String.valueOf(data.get(position).getNumberOfJudges()));
        holder.textScore.setText(String.valueOf(data.get(position).getScore()));
        holder.textRate.setText(String.valueOf(parseScoreToRate(data.get(position).getScore())));
        holder.textHotelName.setText(String.valueOf(data.get(position).getName()));
        holder.imagesHotel.setImageBitmap(hotelImage);
    }

    public String parseScoreToRate(Double textScore) {
        if (textScore <5.0) {
            return "Sucked";
        }
        else if (textScore < 7.0) {
            return "Medium";
        }
        else {
            return "Awesome";
        }
    }
    @Override
    public int getItemCount() {
        return data.size(); // Return the number of hotels in the list
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
