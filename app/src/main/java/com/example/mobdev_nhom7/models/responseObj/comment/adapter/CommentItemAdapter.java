package com.example.mobdev_nhom7.models.responseObj.comment.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.models.responseObj.comment.CommentItem;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.utils.AmountConverter;
import com.example.mobdev_nhom7.utils.BitmapUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CommentItemAdapter extends RecyclerView.Adapter<CommentItemAdapter.CommentItemViewHolder> {
    Context context;
    private List<CommentItem> data;
    public CommentItem getData(int x) {
        return data.get(x);
    }
    public void setData(List<CommentItem> data) {
        this.data.clear();
        this.data.addAll(data);
    }
    public CommentItemAdapter(Context context, ArrayList<CommentItem > data) {
        this.context = context;
        this.data= data;
    }

    @NonNull
    @Override
    public CommentItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_comment, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), ViewHotel.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Add this line

                context.startActivity(intent);
            }
        });
        return new CommentItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CommentItemViewHolder holder, int position) {
        CommentItem commentItem = data.get(position);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        holder.textName.setText(mAuth.getCurrentUser().getDisplayName());
        holder.textComment.setText(commentItem.getFeedbackItem().getComment());
        Log.d("Hotel review", commentItem.getFeedbackItem().getComment().toString());
        holder.create_date.setText(commentItem.getCreated_date());
        holder.comfortJudge.setText(commentItem.getFeedbackItem().getRatingItemList().getComfort().toString());
        holder.cleanlinessJudge.setText(commentItem.getFeedbackItem().getRatingItemList().getCleanliness().toString());
        holder.buildingJudge.setText(commentItem.getFeedbackItem().getRatingItemList().getBuilding().toString());
        holder.valueJudge.setText(commentItem.getFeedbackItem().getRatingItemList().getValue().toString());
    }
    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0; // Return the number of hotels in the list
    }

    public class CommentItemViewHolder extends RecyclerView.ViewHolder{
        TextView textName, textComment, create_date;
        TextView comfortJudge, cleanlinessJudge, buildingJudge, valueJudge;
        public CommentItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.userNameTextView);
            textComment = itemView.findViewById(R.id.commentTextView);
            create_date = itemView.findViewById(R.id.dateRangeTextView);
            comfortJudge = itemView.findViewById(R.id.comfortRate);
            cleanlinessJudge = itemView.findViewById(R.id.cleanlinessRate);
            buildingJudge = itemView.findViewById(R.id.buildingRate);
            valueJudge = itemView.findViewById(R.id.valueRate);
        }
    }

}