package com.example.mobdev_nhom7.models.hotel.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
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
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.example.mobdev_nhom7.utils.AmountConverter;
import com.example.mobdev_nhom7.utils.BitmapUtil;
import com.example.mobdev_nhom7.utils.SendID;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardHotel2Adapter extends RecyclerView.Adapter<CardHotel2Adapter.ListHotelViewHolder> {
    APIService apiService = APIUtils.getUserService();
    Context context;
    private List<SearchHotelItem> data;
    private SharedPreferences preferences;
    private SharedPreferences preferencesHotel;
    SharedPreferences.Editor editor;
    boolean is_loved;
    int count = 0;
    String user_id;

    public SearchHotelItem getData(int x) {
        return data.get(x);
    }

    SendID sendID;

    public CardHotel2Adapter(Context context, List<SearchHotelItem> data, SendID sendID) {
        this.context = context;
        this.data = data;
        this.sendID = sendID;
    }

    private CardHotel2Adapter.OnItemClickListener onItemClickListener;
    Intent intent;
    // Existing code...

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(CardHotel2Adapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ListHotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        preferencesHotel = context.getSharedPreferences("hotel", Context.MODE_PRIVATE);
        editor = preferencesHotel.edit();
        preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        user_id = preferences.getString("user_id", "no user_id");
        intent = new Intent(context.getApplicationContext(), ViewHotel.class);
        intent.putExtra("user_id", user_id);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_hotel_2, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), "Getting hotel details", Toast.LENGTH_LONG).show();
                context.startActivity(intent);
            }
        });
        return new ListHotelViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ListHotelViewHolder holder, int position) {
        try {
            URL url = new URL(data.get(position).getImageItemURL().get(0));
            Log.d("url", url.toString());
            BitmapUtil.ggDriveConverter(url.toString(), holder.imagesHotel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DecimalFormatSymbols customSymbol = new DecimalFormatSymbols(Locale.getDefault());
        customSymbol.setCurrencySymbol("VND");
        DecimalFormat customFormat = new DecimalFormat("###,###", customSymbol);
        String amount = data.get(position).getAmount();
        float score = data.get(position).getScore().getValue();
        String formattedScore = String.format("%.1f", score);

        holder.textHotel.setText("Hotel");
        holder.textHotelName.setText(String.valueOf(data.get(position).getName()));
        holder.textScore.setText(String.valueOf(formattedScore));
        holder.textScore.setBackgroundResource(setScoreColor(score));
        if (amount == null) {
            amount = "10500000";
        }
        holder.textAmount.setText("VNĐ " + customFormat.format(Integer.parseInt(amount)));
        holder.textJudge.setText(AmountConverter.calculate(data.get(position).getScore().getValue()));
        holder.textDistance.setText(data.get(position).getPositionFromCenter() + "km from center");
        Log.d("hotel_id", data.get(position).getHotelId());

        holder.itemView.setOnClickListener(v -> {
            sendID.go(data.get(position).getHotelId(), null, null);
        });
        if (data.get(position).getIs_favorite()) {
            holder.imageFav.setImageResource(R.drawable.favourite_toggle_icon_checked);
        } else {
            holder.imageFav.setImageResource(R.drawable.favourite_toggle_icon);
        }
        String hotel_id = data.get(position).getHotelId();
        intent.putExtra("hotel_id", hotel_id);

        Log.d("Actual Hotel Name", data.get(position).getName());
        Log.d("Actual Hotel ID", data.get(position).getHotelId());
        Log.d("hotel_id Card", hotel_id);
        holder.imageFav.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
            Log.d("position", String.valueOf(position));

            if (data.get(position).getIs_favorite()) {
                holder.imageFav.setImageResource(R.drawable.favourite_toggle_icon);
                //HAVE TO CALL TO
                Log.d("is_favourite", String.valueOf(data.get(position).getIs_favorite()));

                deleteFavHotel(user_id, hotel_id, position);
            } else {
                holder.imageFav.setImageResource(R.drawable.favourite_toggle_icon_checked);

                Log.d("is_favourite", String.valueOf(data.get(position).getIs_favorite()));

                //HAVE TO CALL TO BE
                addFavHotel(user_id, hotel_id);
            }
            // Update the isFav property in the data list
            boolean isFav = data.get(position).getIs_favorite();
            data.get(position).setIs_favorite(!isFav);

            // Notify the adapter about the data set change
            notifyItemChanged(position);
        });

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
        private final ImageView imageFav;

        public ListHotelViewHolder(@NonNull View itemView) {
            super(itemView);
            is_loved = false;
            imageFav = itemView.findViewById(R.id.favouriteIcon2);
            textHotel = itemView.findViewById(R.id.textHotel);
            textHotelName = itemView.findViewById(R.id.textHotelName);
            imagesHotel = itemView.findViewById(R.id.imageHotel);
            textScore = itemView.findViewById(R.id.textScore);
            textJudge = itemView.findViewById(R.id.textJudge);
            textAmount = itemView.findViewById(R.id.textAmount);
            textDistance = itemView.findViewById(R.id.textDistance);
        }
    }

    public void deleteFavHotel(String user_id, String hotel_id, int position) {
        Call<String> call = apiService.deleteFavouriteHotel(user_id, hotel_id);
        String requestUrl = call.request().url().toString();

        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    Log.d("response", response.toString());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("call", t.toString());
                Toast.makeText(context.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addFavHotel(String user_id, String hotel_id) {
        Call<String> call = apiService.addFavouriteHotel(user_id, hotel_id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    Log.d("response", response.toString());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("call", t.toString());
                Toast.makeText(context.getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int setScoreColor(float score) {
        if (score > 8.5) {
            return R.drawable.rating_excellent;
        } else if (score > 7.0) {
            return R.drawable.rating_great;
        } else if (score > 5.0) {
            return R.drawable.rating_acceptable;
        } else {
            return R.drawable.rating_bad;
        }
    }
}