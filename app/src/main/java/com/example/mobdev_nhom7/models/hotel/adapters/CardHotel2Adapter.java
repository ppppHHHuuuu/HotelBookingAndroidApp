package com.example.mobdev_nhom7.models.hotel.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.utils.AmountConverter;
import com.example.mobdev_nhom7.utils.BitmapUtil;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class CardHotel2Adapter extends RecyclerView.Adapter<CardHotel2Adapter.ListHotelViewHolder> {
    Context context;
    private List<SearchHotelItem> data;
    public SearchHotelItem getData(int x) {
        return data.get(x);
    }
    public CardHotel2Adapter(Context context, List<SearchHotelItem> data) {
        this.context =  context;
        this.data= data;
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
        try {
            URL url = new URL(data.get(position).getImageItemURL().get(0));
            Log.d("url", url.toString());
            BitmapUtil.ggDriveConverter(url.toString(), holder.imagesHotel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        holder.textHotel.setText("Hotel");
        holder.textHotelName.setText(String.valueOf(data.get(position).getName()));
        holder.textScore.setText(String.valueOf(data.get(position).getScore().getValue()));
        holder.textAmount.setText(data.get(position).getAmount() + ".000VND");
        holder.textJudge.setText(AmountConverter.calculate(data.get(position).getScore().getValue()));
        holder.textDistance.setText(data.get(position).getPositionFromCenter() + " from center");
    }

    @Override
    public int getItemCount() {
        return data.size(); // Return the number of hotels in the list
    }
    public class ListHotelViewHolder extends RecyclerView.ViewHolder {
        private final TextView textHotel;
//        private final TextView textLocation;
        private final TextView textScore;
        private final ImageView imagesHotel;
        private final TextView textHotelName;
        private final TextView textJudge;
        private final TextView textAmount;
        private final TextView textDistance;
        public ListHotelViewHolder(@NonNull View itemView) {
            super(itemView);
            textHotel = itemView.findViewById(R.id.textHotel);
            textHotelName = itemView.findViewById(R.id.textHotelName);
            imagesHotel = itemView.findViewById(R.id.imageHotel);
            textScore = itemView.findViewById(R.id.textScore);
            textJudge = itemView.findViewById(R.id.textJudge);
            textAmount = itemView.findViewById(R.id.textAmount);
            textDistance = itemView.findViewById(R.id.textDistance);
        }
    }
}
