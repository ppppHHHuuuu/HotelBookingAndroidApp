package com.example.mobdev_nhom7.models.hotel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.models.hotel.RoomType;

import java.util.ArrayList;
import java.util.List;

public class CardRoomTypeAdapter extends RecyclerView.Adapter<CardRoomTypeAdapter.ListRoomTypeHolder> {
    Context context;
    private List<RoomType> data;
    public RoomType getData(int x) {
        return data.get(x);
    }
    public CardRoomTypeAdapter(Context context, ArrayList<RoomType> data) {
        this.data= data;
        this.context = context;
    }

    @NonNull
    @Override
    public ListRoomTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_city_details, parent, false);

        return new CardRoomTypeAdapter.ListRoomTypeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardRoomTypeAdapter.ListRoomTypeHolder holder, int position) {
        String name = data.get(position).getRoomTypeName();
        int capacity = data.get(position).getRoomTypeCapacity();
        int price = data.get(position).getRoomTypePrice();
        int quantity = data.get(position).getRoomTypeQuantity();

        holder.name.setText(name);
        holder.capacity.setText(capacity);
        holder.capacity.setText(price);

    }

    @Override
    public int getItemCount() {
        return data.size(); // Return the number of hotels in the list
    }
    public class ListRoomTypeHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView price;
        private final TextView capacity;
        public ListRoomTypeHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.room_name);
            price=itemView.findViewById(R.id.room_price);
            capacity=itemView.findViewById(R.id.room_persons);

        }
    }


}
