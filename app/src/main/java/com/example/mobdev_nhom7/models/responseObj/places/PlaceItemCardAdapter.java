package com.example.mobdev_nhom7.models.responseObj.places;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityItem;
import com.example.mobdev_nhom7.models.responseObj.cityName.CityItemCardAdapter;
import com.example.mobdev_nhom7.utils.PlaceType;

import java.util.List;
import java.util.Objects;

public class PlaceItemCardAdapter extends RecyclerView.Adapter<PlaceItemCardAdapter.PlaceItemViewHolder> {
    private List<PlaceItem> placeItemList;
    private Context context;

    public PlaceItemCardAdapter(Context context, List<PlaceItem> placeItemList) {
        this.context = context;
        this.placeItemList = placeItemList;
    }
    @NonNull
    @Override
    public PlaceItemCardAdapter.PlaceItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_place, parent, false);
        return new PlaceItemCardAdapter.PlaceItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceItemCardAdapter.PlaceItemViewHolder holder, int position) {
        PlaceItem place = placeItemList.get(position);
        holder.bind(place);
    }

    @Override
    public int getItemCount() {
        return placeItemList.size();
    }

    public class PlaceItemViewHolder extends RecyclerView.ViewHolder {
        private TextView placeTypeIcon;
        private TextView placeName;
        private TextView country;

        public PlaceItemViewHolder(View itemView) {
            super(itemView);
            placeTypeIcon = itemView.findViewById(R.id.placeTypeIcon);
            placeName = itemView.findViewById(R.id.placeName);
            country =  itemView.findViewById(R.id.countryName);
        }

        public void bind(PlaceItem placeItem) {
            convertTypeToIcon(placeItem);
            placeName.setText(placeItem.getName());
            country.setText(placeItem.getCountry());

        }
        private void convertTypeToIcon(PlaceItem placeItem) {
            String type = placeItem.getType();
            if (Objects.equals(type, PlaceType.CITY.getDisplayName())) {
                placeTypeIcon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.placeholder, 0, 0, 0);
            }
            else if (Objects.equals(type,PlaceType.HOTEL.getDisplayName())) {
                placeTypeIcon.setCompoundDrawablesWithIntrinsicBounds(R.drawable.home, 0,0,0);
            }
            else {
                Log.d("convertTypeToIcon", type);
            }
        }
    }
}
