package com.example.mobdev_nhom7.models.responseObj.trips.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.example.mobdev_nhom7.models.responseObj.trips.CancelledHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.PastHotelItem;
import com.example.mobdev_nhom7.utils.BitmapUtil;
import com.example.mobdev_nhom7.utils.SendID;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CardHotelCancelledTripAdapter extends RecyclerView.Adapter<CardHotelCancelledTripAdapter.ListHotelViewHolder>{
    Context context;
    private List<CancelledHotelItem> data;
    public CancelledHotelItem getData(int x) {
        return data.get(x);
    }
    SendID sendID;
    public CardHotelCancelledTripAdapter(Context context,List <CancelledHotelItem> data, SendID sendID) {
        this.context = context;
        this.data= data;
        this.sendID = sendID;
    }

    @NonNull
    @Override
    public CardHotelCancelledTripAdapter.ListHotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_hotel_trip, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), ViewHotel.class);
                Toast.makeText(context.getApplicationContext(), "Getting hotel details", Toast.LENGTH_LONG).show();
                context.startActivity(intent);
            }
        });
        return new CardHotelCancelledTripAdapter.ListHotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHotelCancelledTripAdapter.ListHotelViewHolder holder, int position) {
        String start_date = data.get(position).getStartDate();
        String end_date = data.get(position).getEndDate();
        String dates = parseDate(start_date, end_date);
        String hotelName = data.get(position).getName();
        String amount = data.get(position).getAmount();

        BitmapUtil.ggDriveConverter(data.get(position).getImageURL(), holder.imagesHotel);
        holder.textHotelName.setText(hotelName);
        holder.textDate.setText(dates);
        holder.textAmount.setText(amount + ".000VND");
        holder.itemView.setOnClickListener(v -> {
            sendID.go(data.get(position).getHotel_id(), null, null);
        });
    }

    @Override
    public int getItemCount() {
        return data.size(); // Return the number of hotels in the list
    }
    public class ListHotelViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imagesHotel;
        private final TextView textHotelName;
        private final TextView textAmount;
        private final TextView textDate;
        public ListHotelViewHolder(@NonNull View itemView) {
            super(itemView);
            textHotelName = itemView.findViewById(R.id.textHotelName1);
            imagesHotel = itemView.findViewById(R.id.imageHotel1);
            textAmount = itemView.findViewById(R.id.textAmount1);
            textDate = itemView.findViewById(R.id.textDate1);
        }
    }
    private static String parseDate(String start_date, String end_date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd", Locale.US);

        try {
            Date startDate = inputFormat.parse(start_date);
            Date endDate = inputFormat.parse(end_date);

            String formattedStartDate = outputFormat.format(startDate);
            String formattedEndDate = outputFormat.format(endDate);

            return formattedStartDate + " - " + formattedEndDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid Date";
        }
    }

}
