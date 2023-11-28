package com.example.mobdev_nhom7.models.responseObj.places;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.utils.PlaceType;
import com.example.mobdev_nhom7.utils.SendID;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> implements Filterable {

    private List<PlaceItem> originalData;
    private List<PlaceItem> filteredData;

    private CustomFilter filter;
    private Context context;

    SendID sendID;
    public CustomAdapter(Context context, List<PlaceItem> data, SendID sendID) {
        this.originalData = data;
        this.filteredData = new ArrayList<>(data);
        this.filter = new CustomFilter();
        this.sendID = sendID;
        this.context = context;
    }
    public void clearData() {
        originalData.clear();
        filteredData.clear();
        notifyDataSetChanged();
    }
    public void setData(List<PlaceItem> newData) {
        filteredData.clear();
        filteredData.addAll(newData);
    }
    @Override
    public Filter getFilter() {
        return filter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_place, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PlaceItem placeItem = filteredData.get(position);
        if (Objects.equals(placeItem.getType(), PlaceType.CITY.getDisplayName())) {
            holder.imageType.setBackgroundResource(R.drawable.location_icon);
            holder.itemView.setOnClickListener(v -> sendID.go(null, placeItem.getCity_id()));

        }
        else if (Objects.equals(placeItem.getType(), PlaceType.HOTEL.getDisplayName())) {
            holder.imageType.setBackgroundResource(R.drawable.hotel_icon);
            holder.itemView.setOnClickListener(v -> sendID.go(placeItem.getHotel_id(), null));
        }
        holder.country.setText(placeItem.getCountry());
        holder.place.setText(placeItem.getName());

    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView imageType;
        public TextView place;
        public TextView country;


        public ViewHolder(View view) {
            super(view);
            imageType = view.findViewById(R.id.placeTypeIcon);
            place = view.findViewById(R.id.placeName);
            country = view.findViewById(R.id.countryName);
        }
    }

    private class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<PlaceItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
//                filteredList.addAll(originalData);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (PlaceItem item : originalData) {
                    if (item.getName().toLowerCase().startsWith(filterPattern.toLowerCase())) {
                        filteredList.add(item);
                    }
                }
            }

            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData.clear();
            filteredData.addAll((List<PlaceItem>) results.values);
            notifyDataSetChanged();
        }
    }
}
