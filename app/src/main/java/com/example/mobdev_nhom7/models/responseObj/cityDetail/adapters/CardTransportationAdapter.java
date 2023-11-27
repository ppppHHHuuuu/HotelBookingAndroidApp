package com.example.mobdev_nhom7.models.responseObj.cityDetail.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Transportation;
import com.example.mobdev_nhom7.utils.BitmapUtil;

import java.util.ArrayList;
import java.util.List;

public class CardTransportationAdapter extends RecyclerView.Adapter<CardTransportationAdapter.ListTransportationHolder> {
    Context context;
    private List<Transportation> data;
    public Transportation getData(int x) {
        return data.get(x);
    }
    public CardTransportationAdapter(Context context, ArrayList<Transportation> data) {
        this.data= data;
        this.context = context;
    }

    @NonNull
    @Override
    public CardTransportationAdapter.ListTransportationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_city_details, parent, false);
        return new CardTransportationAdapter.ListTransportationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTransportationAdapter.ListTransportationHolder holder, int position) {
        Bitmap transportationImage = null;
        try {
            transportationImage = BitmapUtil.urlToBitmapConverter(data.get(position).getImage());
            holder.imageTransportation.setImageBitmap(transportationImage);

        }
        catch (Exception e) {
        }


        String content = data.get(position).getContent();
        Float rating = (float) data.get(position).getRating();
        String address = data.get(position).getAddress();


        holder.textName.setText(content);
        holder.textRating.setRating(rating);
        holder.textAddress.setText(address);
    }

    @Override
    public int getItemCount() {
        return data.size(); // Return the number of hotels in the list
    }
    public class ListTransportationHolder extends RecyclerView.ViewHolder {
        private final ImageView imageTransportation;
        private final TextView textName;
        private final RatingBar textRating;
        private final TextView textAddress;
        public ListTransportationHolder(@NonNull View itemView) {
            super(itemView);
            imageTransportation = itemView.findViewById(R.id.image);
            textName = itemView.findViewById(R.id.name);
            textRating = itemView.findViewById(R.id.rating);
            textAddress = itemView.findViewById(R.id.address);
        }
    }
}
