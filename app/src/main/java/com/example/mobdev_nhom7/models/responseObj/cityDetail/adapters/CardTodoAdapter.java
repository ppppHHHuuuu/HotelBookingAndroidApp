package com.example.mobdev_nhom7.models.responseObj.cityDetail.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.ViewCity;
import com.example.mobdev_nhom7.models.responseObj.cityDetail.Todo;
import com.example.mobdev_nhom7.utils.BitmapUtil;

import java.util.ArrayList;
import java.util.List;

public class CardTodoAdapter extends RecyclerView.Adapter<CardTodoAdapter.ListTodoHolder> {
    Context context;
    private List<Todo> data;
    public Todo getData(int x) {
        return data.get(x);
    }
    public CardTodoAdapter(Context context, ArrayList<Todo> data) {
        this.data= data;
        this.context = context;
    }

    @NonNull
    @Override
    public CardTodoAdapter.ListTodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_city_details, parent, false);
        view.setOnClickListener(view1 -> {
            Intent intent = new Intent(context.getApplicationContext(), ViewCity.class);
            Toast.makeText(context.getApplicationContext(), "Getting hotel details", Toast.LENGTH_LONG).show();
            context.startActivity(intent);
        });
        return new CardTodoAdapter.ListTodoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTodoAdapter.ListTodoHolder holder, int position) {
        Bitmap todoImage = null;
        try {
            todoImage = BitmapUtil.urlToBitmapConverter(data.get(position).getImage());
            holder.imageTodo.setImageBitmap(todoImage);

        }
        catch (Exception e) {
            Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }


        String content = data.get(position).getContent();
        Float rating = (float) data.get(position).getRating();
        String address = data.get(position).getAddress();


        holder.textName.setText(content);
        holder.textRating.setRating(rating);
        holder.textAddress.setText(address);
    }

    @Override
    public int getItemCount() {
        return data.size(); // Return the number of hotels in the list
    }
    public class ListTodoHolder extends RecyclerView.ViewHolder {
        private final ImageView imageTodo;
        private final TextView textName;
        private final RatingBar textRating;
        private final TextView textAddress;
        public ListTodoHolder(@NonNull View itemView) {
            super(itemView);
            imageTodo = itemView.findViewById(R.id.image);
            textName = itemView.findViewById(R.id.name);
            textRating = itemView.findViewById(R.id.rating);
            textAddress = itemView.findViewById(R.id.address);
        }
    }

}
