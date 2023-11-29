package com.example.mobdev_nhom7.models.responseObj.trips.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
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
import com.example.mobdev_nhom7.models.hotel.adapters.CardHotel2Adapter;
import com.example.mobdev_nhom7.models.responseObj.trips.ActiveHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.ActiveHotelItem;
import com.example.mobdev_nhom7.models.responseObj.trips.PastHotelItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.example.mobdev_nhom7.utils.BitmapUtil;
import com.example.mobdev_nhom7.utils.SendID;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kotlinx.coroutines.channels.Send;

public class CardHotelActiveTripAdapter extends RecyclerView.Adapter<CardHotelActiveTripAdapter.ListHotelViewHolder> {
    APIService apiService = APIUtils.getUserService();
    Context context;
    SendID sendID;
    private SharedPreferences preferences;
    private SharedPreferences preferencesHotel;
    SharedPreferences.Editor editor;
    String user_id;

    private List<ActiveHotelItem> data;
    public ActiveHotelItem getData(int x) {
        return data.get(x);
    }
    public CardHotelActiveTripAdapter(Context context, ArrayList<ActiveHotelItem> data, SendID sendID) {
        this.data= data;
        this.context = context;
        this.sendID = sendID;
    }
    private CardHotelActiveTripAdapter.OnItemClickListener onItemClickListener;
    Intent intent;
    // Existing code...

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(CardHotelActiveTripAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    @NonNull
    @Override
    public ListHotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        preferencesHotel = context.getSharedPreferences("hotel",Context.MODE_PRIVATE);
        editor = preferencesHotel.edit();
        preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        user_id = preferences.getString("user_id", "no user_id");
        intent = new Intent(context.getApplicationContext(), ViewHotel.class);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        intent.putExtra("user_id", user_id);

        View view = layoutInflater.inflate(R.layout.card_hotel_trip, parent, false);
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
        String start_date = data.get(position).getStartDate();
        String end_date = data.get(position).getEndDate();
        String dates = parseDate(start_date, end_date);
        String hotelName = data.get(position).getName();
        String amount = data.get(position).getAmount();

        BitmapUtil.ggDriveConverter(data.get(position).getImageURL(), holder.imagesHotel);
        Log.d("imageURL", data.get(position).getImageURL());
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
