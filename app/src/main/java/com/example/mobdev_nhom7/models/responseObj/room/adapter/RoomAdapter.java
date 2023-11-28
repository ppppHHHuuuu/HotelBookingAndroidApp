package com.example.mobdev_nhom7.models.responseObj.room.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R; // Change this to the actual package of your R class
import com.example.mobdev_nhom7.models.responseObj.room.RoomItem;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private Context context;
    private List<RoomItem> roomList;

    public RoomAdapter(Context context, List<RoomItem> roomList) {
        this.context = context;
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.button_price, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        RoomItem roomItem = roomList.get(position);

        // Bind data to the views
        holder.roomTextView.setText(roomItem.getRoomName());
        holder.peopleTextView.setText(Integer.parseInt(roomItem.getCapacity())<=1 ? String.format("%s person",roomItem.getCapacity()) : String.format("%s people", roomItem.getCapacity()));
        holder.priceTextView.setText("$" + roomItem.getPricePerNight());
        // You might want to set an image here if you have one

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click if needed
            }
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView roomTextView;
        TextView peopleTextView;
        TextView priceTextView;
        ImageView imageView;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            roomTextView = itemView.findViewById(R.id.room);
            peopleTextView = itemView.findViewById(R.id.people);
            priceTextView = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
