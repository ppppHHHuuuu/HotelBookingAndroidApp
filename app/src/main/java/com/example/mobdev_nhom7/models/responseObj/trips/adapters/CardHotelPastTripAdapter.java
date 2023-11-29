package com.example.mobdev_nhom7.models.responseObj.trips.adapters;

import android.app.Dialog;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Rating;
import android.preference.PreferenceManager;
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
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.models.requestObj.feedback.FeedbackRequest;
import com.example.mobdev_nhom7.models.responseObj.DefaultResponseObj;
import com.example.mobdev_nhom7.models.responseObj.comment.FeedbackItem;
import com.example.mobdev_nhom7.models.responseObj.ratings.RatingItem;
import com.example.mobdev_nhom7.models.responseObj.trips.PastHotelItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.example.mobdev_nhom7.utils.BitmapUtil;
import com.example.mobdev_nhom7.utils.SendID;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardHotelPastTripAdapter extends RecyclerView.Adapter<CardHotelPastTripAdapter.ListHotelViewHolder> {
    APIService apiService = APIUtils.getUserService();
    Context context;
    private List<PastHotelItem> data;
    SendID sendID;
    String user_id;
    SharedPreferences preferences;
    private int currentPosition = -1; // Initialize with an invalid value

    private int getPosition(int x) {return x;}
    public PastHotelItem getData(int x) {
        return data.get(x);
    }
    public CardHotelPastTripAdapter(Context context,List <PastHotelItem> data, SendID sendID) {
        this.data= data;
        this.context = context;
        this.sendID = sendID;
    }
    public interface OnReviewSubmittedListener {
        void onReviewSubmitted();
    }
    private OnReviewSubmittedListener onReviewSubmittedListener;
    public void setOnReviewSubmittedListener(OnReviewSubmittedListener listener) {
        this.onReviewSubmittedListener = listener;
    }

    @NonNull
    @Override
    public ListHotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_hotel_trip_with_comment, parent, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        user_id = preferences.getString("user_id", "empty user_id");


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), ViewHotel.class);
                Toast.makeText(context.getApplicationContext(), "Getting hotel details", Toast.LENGTH_LONG).show();
                context.startActivity(intent);
            }
        });
        return new ListHotelViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ListHotelViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String start_date = data.get(position).getStartDate();
        String end_date = data.get(position).getEndDate();
        String dates = parseDate(start_date, end_date);
        String hotelName = data.get(position).getName();
        String amount = data.get(position).getAmount();
        String comment;

        DecimalFormatSymbols customSymbol = new DecimalFormatSymbols(Locale.getDefault());
        customSymbol.setCurrencySymbol("VND");
        DecimalFormat customFormat = new DecimalFormat("###,###", customSymbol);
        BitmapUtil.ggDriveConverter(data.get(position).getImageURL(), holder.imagesHotel);
        holder.textHotelName.setText(hotelName);
        holder.textDate.setText(dates);
        holder.textAmount.setText("VNĐ " + customFormat.format(Integer.parseInt(amount)));
        Log.d("reservationid", data.get(position).getReservationID());
        Log.d("hotelid", data.get(position).getHotel_id());
        holder.itemView.setOnClickListener(v -> {
            sendID.go(data.get(position).getHotel_id(), null, data.get(position).getReservationID());
        });

        if (data.get(position).getFeedbackItem() == null) {
            Log.d("feedback", "False");
            holder.writeReviewBtn.setEnabled(true);
            holder.writeReviewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openReviewDialog(data.get(position).getReservationID(), position);
                }
            });
        }
        else {
            Log.d("feedback", "True");
            holder.writeReviewBtn.setEnabled(false);
            holder.writeReviewBtn.setBackgroundColor(Color.GRAY); // Example: Set background color to gray
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0; // Return the number of hotels in the list
    }
    public class ListHotelViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imagesHotel;
        private final TextView textHotelName;
        private final TextView textAmount;
        private final TextView textDate;
        LinearLayout writeReviewBtn;
        public ListHotelViewHolder(@NonNull View itemView) {
            super(itemView);
            writeReviewBtn = itemView.findViewById(R.id.review_button);
            textHotelName = itemView.findViewById(R.id.textHotelName2);
            imagesHotel = itemView.findViewById(R.id.imageHotel2);
            textAmount = itemView.findViewById(R.id.textAmount2);
            textDate = itemView.findViewById(R.id.textDate2);
        }
    }
    private static String parseDate(String start_date, String end_date) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd", Locale.US);

        try {
            Date startDate = inputFormat.parse(start_date);
            Date endDate = inputFormat.parse(end_date);

            String formattedStartDate = outputFormat.format(startDate);
            String formattedEndDate = outputFormat.format(endDate);

            return formattedStartDate + " - " + formattedEndDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return "Invalid Date";
        }
    }

    public void openReviewDialog(String reservation_id, int position) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.review_dialog);

        Window window = dialog.getWindow();

        SeekBar valueSeekBar = dialog.findViewById(R.id.valueSeekBar);
        SeekBar cleanlinessSeekBar = dialog.findViewById(R.id.cleanlinessSeekBar);
        SeekBar buildingSeekBar = dialog.findViewById(R.id.buildingSeekBar);
        SeekBar comfortSeekBar = dialog.findViewById(R.id.comfortSeekBar);

        TextView valueSeekBarValue = dialog.findViewById(R.id.valueSeekBarValue);
        TextView cleanlinessSeekBarValue = dialog.findViewById(R.id.cleanlinessSeekBarValue);
        TextView buildingSeekBarValue = dialog.findViewById(R.id.buildingSeekBarValue);
        TextView comfortSeekBarValue = dialog.findViewById(R.id.comfortSeekBarValue);

        EditText reviewEditText = dialog.findViewById(R.id.reviewEdit);

        Button sendButton = dialog.findViewById(R.id.sendButton);

        valueSeekBar.setProgress(50);
        cleanlinessSeekBar.setProgress(50);
        buildingSeekBar.setProgress(50);
        comfortSeekBar.setProgress(50);
        valueSeekBarValue.setText("5.0");
        cleanlinessSeekBarValue.setText("5.0");
        buildingSeekBarValue.setText("5.0");
        comfortSeekBarValue.setText("5.0");
        valueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int STEP = 5;
                int steppedProgress = Math.round(progress / STEP) * STEP;
                valueSeekBar.setProgress(steppedProgress);
                valueSeekBarValue.setText(String.valueOf((float) steppedProgress/10));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        cleanlinessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int STEP = 5;
                int steppedProgress = Math.round(progress / STEP) * STEP;
                cleanlinessSeekBar.setProgress(steppedProgress);
                cleanlinessSeekBarValue.setText(String.valueOf((float) steppedProgress/10));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        buildingSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int STEP = 5;
                int steppedProgress = Math.round(progress / STEP) * STEP;
                buildingSeekBar.setProgress(steppedProgress);
                buildingSeekBarValue.setText(String.valueOf((float) steppedProgress/10));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        comfortSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                int STEP = 5;
                int steppedProgress = Math.round(progress / STEP) * STEP;
                comfortSeekBar.setProgress(steppedProgress);
                comfortSeekBarValue.setText(String.valueOf((float) steppedProgress/10));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float reviewValue = (float) valueSeekBar.getProgress()/10;
                float reviewClean= (float) cleanlinessSeekBar.getProgress()/10;
                float reviewBuilding= (float) buildingSeekBar.getProgress()/10;
                float reviewComfort= (float) comfortSeekBar.getProgress()/10;
                String reviewText = reviewEditText.getText().toString();
                sendReview(reviewValue, reviewClean, reviewBuilding, reviewComfort, reviewText);
                RatingItem ratingItem = new RatingItem();
                ratingItem.setBuilding(reviewBuilding);
                ratingItem.setValue(reviewValue);
                ratingItem.setCleanliness(reviewClean);
                ratingItem.setComfort(reviewComfort);
                createFeedback(reservation_id, ratingItem, reviewText, position);
                data.get(position).setFeedbackItem(new FeedbackItem());
                notifyItemChanged(position);
                Log.d("notify", "true");
                dialog.hide();
            }
        });

        if (window == null) {
            return;
        }



        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.BOTTOM;

        window.setAttributes(windowAttributes);
        dialog.show();
    }

    public void sendReview(float reviewValue, float reviewClean, float reviewBuilding, float reviewComfort, String reviewText) {
        Log.d("Review", "Value SeekBar: " + reviewValue);
        Log.d("Review", "Cleanliness SeekBar: " + reviewClean);
        Log.d("Review", "Building SeekBar: " + reviewBuilding);
        Log.d("Review", "Comfort SeekBar: " + reviewComfort);
        Log.d("Review", "Review Text: " + reviewText);
    }

    public void createFeedback(String reservation_id, RatingItem rating, String comment, int position) {
        FeedbackRequest feedbackRequest = new FeedbackRequest();
        feedbackRequest.setComment(comment);
        feedbackRequest.setRatings(rating);
        feedbackRequest.setReservationId(reservation_id);
        Call<DefaultResponseObj> call = apiService.postUserCommentHotel(feedbackRequest);
        String requestUrl = call.request().url().toString();
        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<DefaultResponseObj>() {
            @Override
            public void onResponse(Call<DefaultResponseObj> call, Response<DefaultResponseObj> response) {
                if (!response.isSuccessful()) {
                    Log.d("response error", String.valueOf(response.code()));
                    return;
                }
                if (response.body() == null) {
                    Log.d("response error", "Empty response");
                    return;
                }

            }

            @Override
            public void onFailure(Call<DefaultResponseObj> call, Throwable t) {
                Toast.makeText(context, R.string.err_network, Toast.LENGTH_SHORT).show();
                Log.d("loadHotel",t.toString());
            }
        });
    }
}
