package com.example.mobdev_nhom7.models.responseObj.room.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R; // Change this to the actual package of your R class
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.models.responseObj.room.RoomItem;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private Context context;
    private List<RoomItem> roomList;
    private AdapterCallback callback;
    private ViewHotel viewHotel;

    public RoomAdapter(Context context, List<RoomItem> roomList, AdapterCallback callback, ViewHotel viewHotel) {
        this.context = context;
        this.roomList = roomList;
        this.callback = callback;
        this.viewHotel = viewHotel;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.button_price, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        RoomItem roomItem = roomList.get(holder.getAdapterPosition());
        String amount = roomItem.getPricePerNight();
        String roomQuantity = roomItem.getQuantity();
        String numberBooked = roomItem.getNumberBooked();
        String totalRoomCost = roomItem.getTotalRoomCost();
        boolean showHidden = roomItem.getShowHidden();

        DecimalFormatSymbols customSymbol = new DecimalFormatSymbols(Locale.getDefault());
        customSymbol.setCurrencySymbol("VND");
        DecimalFormat customFormat = new DecimalFormat("###,###", customSymbol);

        holder.roomTextView.setText(roomItem.getRoomName());
        holder.peopleTextView.setText(Integer.parseInt(roomItem.getCapacity()) <= 1 ? String.format("%s person", roomItem.getCapacity()) : String.format("%s people", roomItem.getCapacity()));
        holder.priceTextView.setText(customFormat.format(Integer.parseInt(amount)));
        holder.numberRoomPicked.setText(numberBooked);
        holder.totalRoomCost.setText(totalRoomCost);

        if (showHidden) {
            holder.hidden.setVisibility(View.VISIBLE);
        } else {
            holder.hidden.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roomName = holder.roomTextView.getText().toString();
                String roomCapacity = holder.peopleTextView.getText().toString();
                String roomPrice = holder.priceTextView.getText().toString();
                openBookRoomDialog(position, roomName, roomCapacity, roomPrice, roomQuantity);
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
        TextView numberRoomPicked;
        TextView totalRoomCost;
        View hidden;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            roomTextView = itemView.findViewById(R.id.room_name);
            peopleTextView = itemView.findViewById(R.id.room_people);
            priceTextView = itemView.findViewById(R.id.room_price);
            numberRoomPicked = itemView.findViewById(R.id.numberRoomPickedTV);
            totalRoomCost = itemView.findViewById(R.id.totalCostTV);
            hidden = itemView.findViewById(R.id.roomBookingHidden);
        }
    }

    public void openBookRoomDialog(int position, String roomName, String roomCapacity, String roomPrice, String roomQuantity) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.book_room_dialog);

        TextView dialogRoomName = dialog.findViewById(R.id.title);
        TextView dialogPickedRoomNum = dialog.findViewById(R.id.picked_room_num);
        TextView dialogTotalRoomPrice = dialog.findViewById(R.id.totalRoomPrice);
        ImageView minusButton = dialog.findViewById(R.id.minusButton);
        ImageView plusButton = dialog.findViewById(R.id.plusButton);
        Button confirmButton = dialog.findViewById(R.id.confirm_button);

        dialogRoomName.setText(roomName);

        Window window = dialog.getWindow();
        final int[] roomCount = {0};
        dialogPickedRoomNum.setText(String.valueOf(roomCount[0]));
        if (window == null) {
            return;
        }

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Decrement the room count
                if (roomCount[0] > 0) {
                    roomCount[0]--;
                    dialogPickedRoomNum.setText(String.valueOf(roomCount[0]));
                    dialogTotalRoomPrice.setText(calculateTotalRoomPrice(roomCount[0], roomPrice));
                }
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roomCount[0] < Integer.parseInt(roomQuantity)) {
                    roomCount[0]++;
                    dialogPickedRoomNum.setText(String.valueOf(roomCount[0]));
                    dialogTotalRoomPrice.setText(calculateTotalRoomPrice(roomCount[0], roomPrice));
                }

            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberRoom = dialogPickedRoomNum.getText().toString();
                String totalRoomCost = dialogTotalRoomPrice.getText().toString();
                String totalCost = String.valueOf(getTotalCost());
                if (Integer.parseInt(numberRoom) > 0) {
                    setDataHiddenCard(position, numberRoom, totalRoomCost, totalCost, true);
                } else {
                    setDataHiddenCard(position, numberRoom, totalRoomCost, totalCost, false);
                }

                Log.d("totalCost", String.valueOf(getTotalCost()));
                DecimalFormatSymbols customSymbol = new DecimalFormatSymbols(Locale.getDefault());
                customSymbol.setCurrencySymbol("VND");
                DecimalFormat customFormat = new DecimalFormat("###,###", customSymbol);
                viewHotel.updateTotalCost(customFormat.format(getTotalCost()));


                dialog.dismiss();
            }
        });

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.BOTTOM;

        window.setAttributes(windowAttributes);

        dialog.show();
    }

    private String calculateTotalRoomPrice(int roomCount, String roomPrice) {
        int totalRoomPrice = roomCount * Integer.parseInt(roomPrice.replaceAll(",", ""));
        DecimalFormatSymbols customSymbol = new DecimalFormatSymbols(Locale.getDefault());
        customSymbol.setCurrencySymbol("VND");
        DecimalFormat customFormat = new DecimalFormat("###,###", customSymbol);

        return String.valueOf(customFormat.format(totalRoomPrice));
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (RoomItem room : roomList) {
            if (room.getTotalRoomCost() != null && Integer.parseInt(room.getNumberBooked()) > 0) {
                totalCost += Integer.parseInt(room.getTotalRoomCost().replaceAll(",", ""));
            }
        }
        return totalCost;
    }

    public interface AdapterCallback {
        void onItemUpdated(int position, String number, String cost, String totalCost, boolean showHidden);
        void updateTotalCost(String totalCost);
    }

    private void setDataHiddenCard(int position, String number, String cost, String totalCost, boolean showHidden) {
        if (callback != null) {
            callback.onItemUpdated(position, number, cost, totalCost, showHidden);
            callback.updateTotalCost(totalCost);
        }
    }
}
