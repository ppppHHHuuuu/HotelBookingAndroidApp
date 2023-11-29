package com.example.mobdev_nhom7.models.hotel.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
import com.example.mobdev_nhom7.utils.SendID;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class CardHotelAdapter extends RecyclerView.Adapter<CardHotelAdapter.FavHotelViewHolder> {
    Context context;
    Intent intent;
    SendID sendID;
    String user_id;
    private SharedPreferences preferences;

    private List<SearchHotelItem> data;
    public SearchHotelItem getData(int x) {
        return data.get(x);
    }
    public void setData(List<SearchHotelItem> data) {
        this.data.clear();
        this.data.addAll(data);
    }
    public CardHotelAdapter(Context context, ArrayList<SearchHotelItem > data, SendID sendID) {
        this.context = context;
        this.data= data;
        this.sendID = sendID;
    }
    private OnItemClickListener onItemClickListener;

    // Existing code...

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    @NonNull
    @Override
    public FavHotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_hotel, parent, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());

        user_id = preferences.getString("user_id", "no user_id");

        intent = new Intent(context.getApplicationContext(), ViewHotel.class);
        intent.putExtra("user_id", user_id);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(intent);
            }
        });
        return new FavHotelViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FavHotelViewHolder holder, int position) {
        SearchHotelItem searchHotelItem = data.get(position);
        if (searchHotelItem != null) {
            try {
                URL url = new URL(data.get(position).getImageItemURL().get(0));
                Log.d("url", url.toString());
                BitmapUtil.ggDriveConverter(url.toString(), holder.imagesHotel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            intent.putExtra("hotel_id", data.get(position).getHotelId());
            holder.textCity.setText(String.valueOf(data.get(position).getCity()));
            holder.textNumberofJudges.setText(String.valueOf(data.get(position).getScore().getCount() + " reviews"));
            double rating = data.get(position).getScore().getValue();
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String formattedRating = decimalFormat.format(rating);
            holder.textScore.setText(formattedRating);
            holder.textScore.setBackgroundResource(setScoreColor(setScoreColor((float) rating)));
            holder.textRate.setText(AmountConverter.calculate(data.get(position).getScore().getValue()));
            holder.textHotelName.setText(String.valueOf(data.get(position).getName()));
            holder.ratingBar.setRating(data.get(position).getStar());
            holder.favouriteIcon.setImageResource(R.drawable.favourite_toggle_icon_checked);
            holder.favouriteIcon.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
                }
                if (holder.is_loved) {
                    holder.favouriteIcon.setImageResource(R.drawable.favourite_toggle_icon);
                } else {
                    holder.favouriteIcon.setImageResource(R.drawable.favourite_toggle_icon_checked);
                }
                holder.is_loved = !holder.is_loved;
            });

        }

    }
    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0; // Return the number of hotels in the list
    }

    public class FavHotelViewHolder extends RecyclerView.ViewHolder{
        TextView textCity, textScore, textHotelName, textRate, textNumberofJudges;
        RatingBar ratingBar;
        ImageView imagesHotel;
        ImageView favouriteIcon;
        Boolean is_loved;
        public FavHotelViewHolder(@NonNull View itemView) {
            super(itemView);
            is_loved = true;

            favouriteIcon = itemView.findViewById(R.id.favouriteIcon);
            textCity = itemView.findViewById(R.id.textCity);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            textScore = itemView.findViewById(R.id.textScore);
            textHotelName = itemView.findViewById(R.id.textHotelName);
            textRate = itemView.findViewById(R.id.textRate);
            textNumberofJudges = itemView.findViewById(R.id.textNumberofJudges);
            imagesHotel = itemView.findViewById(R.id.imageView);
        }
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
