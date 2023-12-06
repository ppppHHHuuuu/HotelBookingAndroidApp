package com.example.mobdev_nhom7.activity;

import static android.app.PendingIntent.getActivity;
import static java.sql.Types.NULL;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.fragment.main_activity.StaysFragment;
import com.example.mobdev_nhom7.fragment.main_activity.TripsFragment;
import com.example.mobdev_nhom7.models.bookingRequest.BookingRequest;
import com.example.mobdev_nhom7.models.responseObj.comment.CommentItem;
import com.example.mobdev_nhom7.models.responseObj.comment.adapter.CommentItemAdapter;
import com.example.mobdev_nhom7.models.responseObj.hotel.HotelItem;
import com.example.mobdev_nhom7.models.responseObj.room.RoomItem;
import com.example.mobdev_nhom7.models.responseObj.room.adapter.RoomAdapter;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.example.mobdev_nhom7.utils.AmountConverter;
import com.example.mobdev_nhom7.utils.BitmapUtil;
import com.google.firebase.auth.FirebaseAuth;

import org.checkerframework.checker.units.qual.C;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewHotel extends Activity implements RoomAdapter.AdapterCallback {
    private APIService apiService = APIUtils.getUserService();
    SharedPreferences preferences;
    String user_id;
    String hotel_id;
    String startDate;
    String endDate;
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
    private TextView totalCostTV;
    private Button bookButton;
    private StaysFragment staysFragment;

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
        contactTitle = findViewById(R.id.phoneNumber);
        totalCostTV = findViewById(R.id.totalCost);
        bookButton = findViewById(R.id.bookButton);

        commentItemAdapter = new CommentItemAdapter(getApplicationContext(), (ArrayList<CommentItem>) commentItems);
        commentRecyclerView.setAdapter(commentItemAdapter);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        roomAdapter = new RoomAdapter(ViewHotel.this, roomItems, this, this);
        priceLayout.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        priceLayout.setAdapter(roomAdapter);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        user_id = preferences.getString("user_id", "no user_id");

        Bundle extras = this.getIntent().getExtras();
        Log.d("view Hotel", "start");
        if (extras == null) {
            Log.d("extra", "null");
            hotel_id = "obpw61GK8OYRO11TsdB7";
            Log.d("hotel_id", "null");

        } else {
            for (String key : extras.keySet()) {
                Log.e("extras", key + " : " + (extras.get(key) != null ? extras.get(key) : "NULL"));
            }
            if (extras.getString("hotel_id") != null) {
                hotel_id = extras.getString("hotel_id");
                Log.d("hotel_id View Hotel", hotel_id);
            }
//
            if (extras.getString("startDate") != null) {
                startDate = extras.getString("startDate");
//                startDate = "2023-12-01";
                Log.d("startDate", startDate);
            }

            if (extras.getString("endDate") != null) {
                endDate = extras.getString("endDate");
//                startDate = "2023-12-03";

                Log.d("endDate", endDate);
            }
        }

        bookButton.setOnClickListener(view -> bookHotel());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (startDate == null) {
            startDate = "2023-12-03";
        }
        if (endDate == null) {
            endDate = "2023-12-07";
        }

        //fake-data
        getHotelInRange(hotel_id, startDate, endDate);
        Log.d("startDate", startDate);
        Log.d("endDate", endDate);
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
                Log.d("loadHotel", t.toString());
            }
        });
    }

    private void getHotelInRange(String hotel_id, String start_date, String end_date) {
        Call<HotelItem> call = apiService.getHotelInRange(hotel_id, start_date, end_date);
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
                    Log.d("roomItem", roomItem.getQuantity().toString());
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

    @Override
    public void onItemUpdated(int position, String number, String cost, String totalCost, boolean showHidden) {
        RoomItem roomItem = roomItems.get(position);
        roomItem.setNumberBooked(number);
        roomItem.setTotalRoomCost(cost);
        roomItem.setShowHidden(showHidden);
        roomAdapter.notifyItemChanged(position);
    }

    @Override
    public void updateTotalCost(String totalCost) {
        totalCostTV.setText(totalCost);
    }

    public void bookHotel() {
        Map<String, Integer> rooms = new HashMap<String, Integer>();
        for (RoomItem room : roomItems) {
            if (room.getNumberBooked() != null && Integer.parseInt(room.getNumberBooked()) > 0) {
                rooms.put(room.getRoomName(), Integer.parseInt(room.getNumberBooked()));
            }
        }

        int totalCostFinal = roomAdapter.getTotalCost();

        for (Map.Entry<String, Integer> entry : rooms.entrySet()) {
            Log.d("reservationRoomTestLog", entry.getKey() + " + " + entry.getValue() + " + " + String.valueOf(totalCostFinal));
        }
//        startDate = "2023-12-03";
//        endDate = "2023-12-07";

//        if (startDate == null) {
//            startDate = "2023-12-01";
//        }
//        if (endDate == null) {
//            endDate = "2023-12-03";
//        }

//        long totalCost = totalCostFinal * countDaysBetween(startDate, endDate);
        totalCostTV.setText(String.valueOf(totalCostFinal));
        BookingRequest bookingRequest = new BookingRequest(user_id, hotel_id, rooms, startDate, endDate, totalCostFinal);
        Call<Object> callBooking = apiService.booking(bookingRequest);
        callBooking.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Toast.makeText(getApplicationContext(), "Booking successfully", Toast.LENGTH_SHORT).show();
                Log.d("booking", "Booking successfully");
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
                Log.d("booking", t.toString());
            }
        });

        Intent intent = new Intent(ViewHotel.this, MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_NAVIGATE_TO_TRIPS, true);
        startActivity(intent);
        finish();
    }

    public static long countDaysBetween(String startDateStr, String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        return ChronoUnit.DAYS.between(startDate, endDate);
    }
}