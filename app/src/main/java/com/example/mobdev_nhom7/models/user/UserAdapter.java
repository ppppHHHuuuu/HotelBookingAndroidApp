package com.example.mobdev_nhom7.models.user;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ListUserViewHolder> {
    Context context;
    ArrayList<UserItem> users;

    public UserAdapter(Context context,
                       ArrayList<UserItem> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public ListUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_hotel_2, parent, false);
        return new ListUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUserViewHolder holder, int position) {
        UserItem userItem = users.get(position);
        holder.textName.setText(String.valueOf(userItem.getName()));
        holder.textUid.setText(String.valueOf(userItem.getUid()));
        holder.textEmail.setText(String.valueOf(userItem.getEmail()));
        holder.textPhone.setText(String.valueOf(userItem.getPhone()));
        holder.textProvider.setText(String.valueOf(userItem.getProvider()));
        holder.textAge.setText(String.valueOf(userItem.getAge()));
        holder.textAddress.setText(String.valueOf(userItem.getAddress()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ListUserViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layoutIntroduce;
        private TextView textName;
        private TextView textUid;
        private TextView textEmail;
        private TextView textPhone;
        private TextView textProvider;
        private TextView textAge;
        private TextView textAddress;

        public ListUserViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize variables with their corresponding itemViews
            layoutIntroduce = itemView.findViewById(R.id.layoutIntroduce);
            textName = itemView.findViewById(R.id.textHotel);
            textUid = itemView.findViewById(R.id.textHotelName);
            textEmail = itemView.findViewById(R.id.textScore);
            textPhone = itemView.findViewById(R.id.textJudge);
            textProvider = itemView.findViewById(R.id.textDistance);
            textAge = itemView.findViewById(R.id.textAmount);
            textAddress = itemView.findViewById(R.id.textLastBookingTime);
        }
    }
}
