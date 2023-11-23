package com.example.mobdev_nhom7.models.responseObj.trips.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.models.responseObj.trips.HistoryHotelItem;
import com.example.mobdev_nhom7.utils.BitmapUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CardHotelHistoryTripAdapter extends RecyclerView.Adapter<CardHotelHistoryTripAdapter.ListHotelViewHolder> {
    Context context;
    private List<HistoryHotelItem> data;
    public HistoryHotelItem getData(int x) {
        return data.get(x);
    }
    public CardHotelHistoryTripAdapter(ArrayList<HistoryHotelItem> data) {
        this.data= data;
    }

    @NonNull
    @Override
    public ListHotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_hotel_trip_with_comment, parent, false);
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
        Bitmap hotelImage = BitmapUtil.urlToBitmapConverter(data.get(position).getImageURL());

        String start_date = data.get(position).getStartDate();
        String end_date = data.get(position).getEndDate();
        String dates = parseDate(start_date, end_date);

        String comment= data.get(position).getComment();
        String hotelName = data.get(position).getName();
        String amount = data.get(position).getAmount();

        if (comment!= null && !comment.equals("")) {
            holder.editTextComment.setText(comment);
        }
        holder.textHotelName.setText(hotelName);
        holder.imagesHotel.setImageBitmap(hotelImage);
        holder.textAmount.setText(amount);
        holder.textDate.setText(dates);
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
        private final EditText editTextComment;
        public ListHotelViewHolder(@NonNull View itemView) {
            super(itemView);
            textHotelName = itemView.findViewById(R.id.textHotelName2);
            imagesHotel = itemView.findViewById(R.id.imageHotel2);
            textAmount = itemView.findViewById(R.id.textAmount2);
            textDate = itemView.findViewById(R.id.textDate2);
            editTextComment = itemView.findViewById(R.id.edit_text_comment);
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
