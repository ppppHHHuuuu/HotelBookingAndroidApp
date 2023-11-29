package com.example.mobdev_nhom7.activity;

import static androidx.test.InstrumentationRegistry.getContext;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.models.responseObj.comment.CommentItem;
import com.example.mobdev_nhom7.models.responseObj.comment.adapter.CommentItemAdapter;
import com.example.mobdev_nhom7.models.responseObj.hotel.HotelItem;
import com.example.mobdev_nhom7.models.responseObj.room.RoomItem;
import com.example.mobdev_nhom7.models.responseObj.room.adapter.RoomAdapter;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.example.mobdev_nhom7.utils.AmountConverter;
import com.example.mobdev_nhom7.utils.BitmapUtil;

import org.checkerframework.checker.units.qual.C;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewHotel extends Activity {
    private APIService apiService = APIUtils.getUserService();
    SharedPreferences preferences;
    String user_id;
    String hotel_id;
    RecyclerView commentRecyclerView;
    List<CommentItem> commentItems;
    List<RoomItem> roomItems;
    RoomAdapter roomAdapter;
    CommentItemAdapter commentItemAdapter;
    HotelItem hotelItem;
    private ImageView imageHotel;
    private TextView hotelName;
    private View priceSeparator;
    private TextView pricesTitle;
    private RecyclerView priceLayout;
    private View ratingSeparator;
    private TextView ratingValue;
    private LinearLayout ratingLayout;
    private ProgressBar ratingPointProgressBar;
    private TextView judgeTextView;
    private ProgressBar moneyProgressBar;
    private ProgressBar cleanlinessProgressBar;
    private ProgressBar buildingProgressBar;
    private ProgressBar comfortProgressBar;
    private TextView reviewRateTextView;
    private TextView hotelDescriptionTextView;
    private TextView reviewInfoTextView;
    private TextView locationTitle;
    private TextView textLocation;
    private TextView amenitiesTitle;
    private ImageView wifiInLobbyImageView;
    private TextView wifiInLobbyTextView;
    private ImageView wifiInRoomImageView;
    private TextView wifiInRoomTextView;
    private ImageView spaImageView;
    private TextView spaTextView;
    private ImageView telephoneImageView;
    private TextView telephoneTextView;
    private ImageView poolImageView;
    private TextView poolTextView;
    private ImageView petImageView;
    private TextView petTextView;
    private TextView contactTitle;
    private ImageView contactIconImageView;
    private TextView phoneNumberTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_hotel);
        commentItems = new ArrayList<>();
        hotelItem = new HotelItem();
        roomItems = new ArrayList<>();
        imageHotel = findViewById(R.id.imageHotel);
        hotelName = findViewById(R.id.hotelName);
        priceLayout = findViewById(R.id.priceLayout);
        ratingLayout = findViewById(R.id.ratingLayout);
        ratingValue = findViewById(R.id.ratingPoint);
        judgeTextView = findViewById(R.id.judge);
        moneyProgressBar = findViewById(R.id.moneyProgressBar);
        cleanlinessProgressBar = findViewById(R.id.cleanlinessProgressBar);
        buildingProgressBar = findViewById(R.id.buildingProgressBar);
        comfortProgressBar = findViewById(R.id.comfortProgressBar);
        commentRecyclerView = findViewById(R.id.commentRecyclerView);
        textLocation = findViewById(R.id.textLocation);
        wifiInLobbyImageView = findViewById(R.id.wifiInLobby);
        wifiInRoomImageView = findViewById(R.id.wifiInRoom);
        telephoneImageView = findViewById(R.id.telephone);
        poolImageView = findViewById(R.id.pool);
        petImageView = findViewById(R.id.pet);
        contactTitle =findViewById(R.id.phoneNumber);
        commentItemAdapter= new CommentItemAdapter(getApplicationContext(), (ArrayList<CommentItem>) commentItems);
        commentRecyclerView.setAdapter(commentItemAdapter);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        roomAdapter = new RoomAdapter(getApplicationContext(), roomItems);
        priceLayout.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        priceLayout.setAdapter(roomAdapter);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        user_id = preferences.getString("user_id", "no user_id");

        Bundle extras = this.getIntent().getExtras();
        if(extras == null) {
            Log.d("extra", "null");
            hotel_id= "obpw61GK8OYRO11TsdB7";
            Log.d("hotel_id", "null");

        } else {
            for (String key : extras.keySet()) {
                Log.e("extras", key + " : " + (extras.get(key) != null ? extras.get(key) : "NULL"));
            }
            if (extras.getString("hotel_id") != null) {
                hotel_id = extras.getString("hotel_id");
                Log.d("hotel_id View Hotel", hotel_id);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //fake-data
        getHotelInRange(hotel_id, "2023-11-28", "2023-11-30");
    }
    private void getAllComment(String hotel_id) {
        Call<List<CommentItem>> call = apiService.getAllFeedback(hotel_id);
        String requestUrl = call.request().url().toString();
        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<List<CommentItem>>() {
            @Override
            public void onResponse(Call<List<CommentItem>> call, Response<List<CommentItem>> response) {
                Log.d("Response", response.body().toString());
                if (!response.isSuccessful()) {
                    Log.d("response error", String.valueOf(response.code()));
                    return;
                }
                if (response.body() == null) {
                    Log.d("response error", "Empty response");
                    return;
                }
                commentItems.clear();
                commentItems.addAll(response.body());
                commentItemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CommentItem>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
                Log.d("loadHotel",t.toString());
            }
        });
    }
    private void getHotelInRange(String hotel_id, String start_date, String end_date) {
        Call<HotelItem> call = apiService.getHotelInRange(hotel_id, start_date, end_date);
        String requestUrl = call.request().url().toString();
        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<HotelItem>() {
            @Override
            public void onResponse(@NonNull Call<HotelItem> call, @NonNull Response<HotelItem> response) {
                Log.d("Response", response.body().toString());
                if (!response.isSuccessful()) {
                    Log.d("response error", String.valueOf(response.code()));
                    return;
                }
                if (response.body() == null) {
                    Log.d("response error", "Empty response");
                    return;
                }

                hotelItem = response.body();
                BitmapUtil.ggDriveConverter(hotelItem.getImageURL().get(0), imageHotel);
                roomItems.clear();
                Map<String, RoomItem> rooms = hotelItem.getRooms();
                for (Map.Entry<String, RoomItem> entry : rooms.entrySet()) {
                    RoomItem roomItem = new RoomItem();
                    roomItem.setRoomName(entry.getKey());
                    roomItem.setCapacity(entry.getValue().getCapacity());
                    roomItem.setPricePerNight(entry.getValue().getPricePerNight());
                    roomItem.setQuantity(entry.getValue().getQuantity());
                    roomItems.add(roomItem);
                    Log.d("entry", entry.getKey() + " + " + entry.getValue().getCapacity());
                }
                roomAdapter.notifyDataSetChanged();

                hotelName.setText(hotelItem.getName());
                double rating = hotelItem.getRating().getValue();
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                String formattedRating = decimalFormat.format(rating);

                ratingValue.setText(formattedRating);
                moneyProgressBar.setProgress(hotelItem.getRating().getValue().intValue());
                buildingProgressBar.setProgress(hotelItem.getRating().getBuilding().intValue());
                cleanlinessProgressBar.setProgress(hotelItem.getRating().getCleanliness().intValue());
                comfortProgressBar.setProgress(hotelItem.getRating().getComfort().intValue());
                textLocation.setText(hotelItem.getLocation());

                getAllComment(hotel_id);

                judgeTextView.setText(AmountConverter.calculate(Double.parseDouble(hotelItem.getRating().getValue().toString())));
                if (hotelItem.getAmentinies().isPet()) {
                    petImageView.setImageResource(R.drawable.pet);
                } else {
                    petImageView.setImageResource(R.drawable.no_animals);
                }
                if (hotelItem.getAmentinies().isPool()) {
                    poolImageView.setImageResource(R.drawable.pool);
                } else {
                    poolImageView.setImageResource(R.drawable.no_swimming);
                }
                if (hotelItem.getAmentinies().isTelephone()) {
                    telephoneImageView.setImageResource(R.drawable.telephone);
                } else {
                    telephoneImageView.setImageResource(R.drawable.no_call);
                }
                if (hotelItem.getAmentinies().isWifiInLobby()) {
                    wifiInLobbyImageView.setImageResource(R.drawable.wifi);
                } else {
                    wifiInLobbyImageView.setImageResource(R.drawable.no_wifi);
                }
                if (hotelItem.getAmentinies().isWifiInRoom()) {
                    wifiInRoomImageView.setImageResource(R.drawable.wifi);
                } else {
                    wifiInRoomImageView.setImageResource(R.drawable.no_wifi);
                }
                contactTitle.setText(hotelItem.getContact().toString());

            }

            @Override
            public void onFailure(Call<HotelItem> call, Throwable t) {
                Log.d("call", t.toString());
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}