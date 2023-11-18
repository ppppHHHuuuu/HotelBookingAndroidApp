package com.example.mobdev_nhom7.models.hotel.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.async.ImageLoadTask;
import com.example.mobdev_nhom7.models.responseObj.hotel.HotelItem;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.utils.AmountConverter;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CardHotel2Adapter extends RecyclerView.Adapter<CardHotel2Adapter.ListHotelViewHolder> {
    Context context;
    private List<SearchHotelItem> data = new ArrayList<>();
    public CardHotel2Adapter(ArrayList<SearchHotelItem> data) {
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
        Bitmap hotelImage;
        try {
            URL url = new URL(data.get(position).getImageURL());
            hotelImage = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        holder.textHotel.setText("Hotel");
        holder.textHotelName.setText(String.valueOf(data.get(position).getName()));
        holder.textLocation.setText(String.valueOf(data.get(position).getDistance()));
        holder.textScore.setText(String.valueOf(data.get(position).getScore()));
        holder.imagesHotel.setImageBitmap(hotelImage);
        holder.textAmount.setText(String.valueOf(data.get(position).getAmount()));
        holder.textJudge.setText(AmountConverter.calculate(data.get(position).getScore()));
        holder.textDistance.setText(String.valueOf(data.get(position).getPositionFromCenter()));
    }

    @Override
    public int getItemCount() {
        return data.size(); // Return the number of hotels in the list
    }
    public class ListHotelViewHolder extends RecyclerView.ViewHolder {
        private final TextView textHotel;
        private final TextView textLocation;
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
            textLocation = itemView.findViewById(R.id.textLocation);
            textScore = itemView.findViewById(R.id.textScore);

            textJudge = itemView.findViewById(R.id.textJudge);
            textAmount = itemView.findViewById(R.id.textAmount);
            textDistance = itemView.findViewById(R.id.textDistance);
        }
    }
}
