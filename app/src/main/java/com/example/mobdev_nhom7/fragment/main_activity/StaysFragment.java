package com.example.mobdev_nhom7.fragment.main_activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.ViewHotel;
import com.example.mobdev_nhom7.models.hotel.adapters.CardHotel2Adapter;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.remote.APIService;
import com.example.mobdev_nhom7.remote.APIUtils;
import com.example.mobdev_nhom7.utils.SendID;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaysFragment extends Fragment {
    private APIService apiService = APIUtils.getUserService();
    SharedPreferences preferences;
    SharedPreferences preferencesEdittext;
    SharedPreferences.Editor editorHotel;
    CardHotel2Adapter cardHotel2Adapter;
    ArrayList<SearchHotelItem> searchHotelItems;
    private ProgressBar loadingProgressBar;
    int count = 0;
    private RecyclerView recyclerView;
    private Button buttonSearch;
    private TextView desInput;
    private TextView roomsDisplay;
    private TextView dateDisplay;
    private String hotelID;
    private String destination;
    private String startDate;
    private String endDate;
    private String roomNumber;
    private String pplNumber;
    private String user_id;
    SendID sendID;
    public StaysFragment() {
        // Required empty public constructor
    }

    public static StaysFragment newInstance(String param1, String param2) {
        StaysFragment fragment = new StaysFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stays, container, false);
        ConstraintLayout roomInfoOptions = view.findViewById(R.id.room_info_options);
        ConstraintLayout dateOptions = view.findViewById(R.id.date_options);
        searchHotelItems = new ArrayList<>();
        preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
        preferencesEdittext = getContext().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        loadingProgressBar = view.findViewById(R.id.loadingProgressBar);

        cardHotel2Adapter = new CardHotel2Adapter(requireContext(), searchHotelItems, new SendID() {
            @Override
            public void go(String hotel_id, String city_id, String reservation_id) {
//                Intent intent = new Intent(getContext(), ViewHotel.class);
//                intent.putExtra("hotel_id", hotel_id);
//                startActivity(intent);
                Intent intent = new Intent(getContext(), ViewHotel.class);
                Bundle extras = new Bundle();
                extras.putString("startDate", startDate);
                extras.putString("endDate", endDate);
                extras.putString("hotel_id", hotel_id);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        cardHotel2Adapter.setOnItemClickListener(position -> {
            SearchHotelItem deletedItem = cardHotel2Adapter.getData(position);
            deleteFavouriteHotel(user_id, deletedItem.getHotelId(), position);
        });
        roomInfoOptions.setOnClickListener(view12 -> openRoomOptionsDialog());
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd", Locale.US);
        String formattedToday = dateFormat.format(today);
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        String formattedTomorrow = dateFormat.format(tomorrow);

        dateDisplay = view.findViewById(R.id.dateDisplay);
        dateDisplay.setText(formattedToday + " - " + formattedTomorrow);

        String fullDate = dateDisplay.getText().toString();
        String checkInDate = fullDate.substring(0,6);
        String checkOutDate = fullDate.substring(9,15);
        dateOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDateOptionsDialog(dateDisplay);
            }
        });
        desInput = view.findViewById(R.id.desInput);

        dateDisplay = view.findViewById(R.id.dateDisplay);
        roomsDisplay = view.findViewById(R.id.roomsDisplay);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cardHotel2Adapter);

        buttonSearch = view.findViewById(R.id.buttonSearch);

        //NOTE: Default search value
        SimpleDateFormat dateFullFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        user_id = preferences.getString("user_id", "empty user_id");
        hotelID = "null";
        //TODO: ID OF HANOI
        destination = "Z6YyrwkuyVbsyaLxOE7E"; //aka Hanoi
        startDate = dateFullFormat.format(today);
        endDate = dateFullFormat.format(tomorrow);
        roomNumber = "2";
        pplNumber = "1";
        buttonSearch.setOnClickListener(view1 -> searchHotels(user_id, hotelID, destination, startDate, endDate, roomNumber, pplNumber));
        Log.d("user_id", user_id);
        Log.d("hotelID", hotelID);
        Log.d("destination", destination);
        Log.d("start_date", startDate);
        Log.d("end_date", endDate);
        Log.d("room_quantity", roomNumber);
        Log.d("ppl_quantity", pplNumber);
        desInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEditTextContent();
                getSuggestedDestinationActivity();
            }
        });
        getSuggestDest();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Map<String, ?> allPreferences = preferencesEdittext.getAll();

        for (Map.Entry<String, ?> entry : allPreferences.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Log.d("SharedPreferences", "Key: " + key + ", Value: " + value);
        }
        String savedDestination = preferencesEdittext.getString("destination", "");
        String savedDestinationID = preferencesEdittext.getString("destinationID", "");
        desInput.setText(savedDestination);
        Boolean isSearch = preferencesEdittext.getBoolean("search", false);
        if (isSearch) {
            Log.d("destinationID", savedDestinationID);
            searchHotels(user_id, null, savedDestinationID,startDate, endDate, roomNumber, pplNumber);
        }
    }
    private void saveEditTextContent() {
        String editTextContent = desInput.getText().toString();

        // Use SharedPreferences to save the content
        SharedPreferences preferences = getActivity().getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("destination", editTextContent);
        editor.apply();
    }
    public void getSuggestedDestinationActivity() {
        Intent intent = new Intent(getContext(), SuggestedDestinationActivity.class);
        startActivity(intent);
    }
    public void openRoomOptionsDialog() {
        final Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.room_option_pop_up);

        Button confirmBtn = dialog.findViewById(R.id.confirm_button);
        EditText roomNum = dialog.findViewById(R.id.room_number_edit_text);
        EditText guestNum = dialog.findViewById(R.id.guest_number_edit_text);

        Window window = dialog.getWindow();

        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;

        window.setAttributes(windowAttributes);


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRoomType(roomNum.getText().toString(), guestNum.getText().toString());
                dialog.hide();
                roomNumber = roomNum.getText().toString();
                pplNumber = guestNum.getText().toString();
            }
        });
        dialog.show();
    }
    public void openDateOptionsDialog(TextView dateDisplay) {
        final Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.date_option_pop_up);

        Window window = dialog.getWindow();

        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;

        window.setAttributes(windowAttributes);
        TextView checkIn = dialog.findViewById(R.id.check_in_date_picker);
        TextView checkOut = dialog.findViewById(R.id.check_out_date_picker);

        String fullDate = dateDisplay.getText().toString();
        String checkInDate = fullDate.substring(0,6);
        String checkOutDate = fullDate.substring(9,15);
        checkIn.setText(checkInDate);
        checkOut.setText(checkOutDate);
        checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(checkIn);
            }
        });

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(checkOut);
            }
        });
        Button confirmBtn = dialog.findViewById(R.id.confirm_button);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start = checkIn.getText().toString();
                String end = checkOut.getText().toString();

                // Parse the dates for comparison
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd", Locale.US);
                try {
                    Date startDate = dateFormat.parse(start);
                    Date endDate = dateFormat.parse(end);

                    // Check if start date is greater than end date
                    if (startDate.compareTo(endDate) > 0) {
                        // If start date is greater, set end date to start date + 1 day
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(startDate);
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                        endDate = calendar.getTime();

                        // Update the checkOut TextView with the new end date
                        checkOut.setText(dateFormat.format(endDate));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                setDate(checkIn.getText().toString(), checkOut.getText().toString());
                dialog.hide();
                startDate = checkIn.getText().toString();
                endDate = checkOut.getText().toString();
            }
        });
        dialog.show();
    }
    public void showDatePicker(TextView tv) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(selectedYear, selectedMonth, selectedDay);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd", Locale.US);
                        String formattedDate = dateFormat.format(selectedCalendar.getTime());

                        // Update the EditText with the formatted date
                        tv.setText(formattedDate);
                    }
                },
                year, month, dayOfMonth);

        // Show the DatePickerDialog
        datePickerDialog.show();
    }
    public void setDate(String in, String out) {
        dateDisplay.setText(in + " - " + out);
    }
    public void setRoomType(String roomNum, String guestNum) {
        String res = roomNum;
        int roomNumInt = Integer.parseInt(roomNum);
        int guestNumInt = Integer.parseInt(guestNum);
        if (roomNumInt <= 1) {
            res += " room, ";
        } else {
            res += " rooms, ";
        }

        res += guestNum;

        if (guestNumInt <= 1) {
            res += " guest";
        } else {
            res += " guests";
        }

        roomsDisplay.setText(res);
    }
    public void searchHotels(String user_id, String hotelID, String destination, String start_date, String end_date, String room_quantity, String ppl_quantity ) {
        // Show loading circle
        loadingProgressBar.setVisibility(View.VISIBLE);

        // Hide RecyclerView
        recyclerView.setVisibility(View.GONE);
        Call<List<SearchHotelItem>> callHotel = apiService.searchHotels(user_id, hotelID,destination, start_date, end_date, room_quantity, ppl_quantity);
        String requestUrl = callHotel.request().url().toString();
        Log.d("Request URL", requestUrl);
        callHotel.enqueue(new Callback<List<SearchHotelItem>>() {
            @Override
            public void onResponse(Call<List<SearchHotelItem>> call, Response<List<SearchHotelItem>> response) {
                // Hide loading circle on response
                loadingProgressBar.setVisibility(View.GONE);

                // Show RecyclerView
                recyclerView.setVisibility(View.VISIBLE);
                if (!response.isSuccessful()) {
                    Log.d("response error", String.valueOf(response.code()));
                    return;
                }
                if (response.body() == null) {
                    Log.d("response error", "Empty response");
                    return;
                }
                if (response.body().size() == 0) {
                    Toast.makeText(getContext(), "NO SEARCH FOUND", Toast.LENGTH_LONG).show();
                    return;
                }
                searchHotelItems.clear();
                searchHotelItems.addAll(response.body());
                cardHotel2Adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<SearchHotelItem>> call, Throwable t) {
                // Hide loading circle on failure
                loadingProgressBar.setVisibility(View.GONE);

                // Show RecyclerView
                recyclerView.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), R.string.err_network, Toast.LENGTH_SHORT).show();
                Log.d("loadHotel",t.toString());
            }
        });
    }
    public void getSuggestDest() {
        Call<List<SearchHotelItem>> call = apiService.getSuggestedHotel(user_id);
        String requestUrl = call.request().url().toString();
        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<List<SearchHotelItem>>() {
            @Override
            public void onResponse(@NonNull Call<List<SearchHotelItem>> call, @NonNull Response<List<SearchHotelItem>> response) {
                if (!response.isSuccessful()) {
                    Log.d("response error", String.valueOf(response.code()));
                    return;
                }
                if (response.body() == null) {
                    Log.d("response error", "Empty response");
                    return;
                }
                if (response.body().size() == 0) {
                    Toast.makeText(getContext(), "NO SEARCH FOUND", Toast.LENGTH_LONG).show();
                    return;
                }
                searchHotelItems.clear();
                searchHotelItems.addAll(response.body());
                cardHotel2Adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<SearchHotelItem>> call, Throwable t) {
                Log.d("call", t.toString());

                // Check if the fragment is attached to an activity
                if (isAdded() && getContext() != null) {
                    Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void deleteFavouriteHotel(String user_id, String hotel_id, int position) {
        Call<String> call = apiService.deleteFavouriteHotel(user_id, hotel_id);
        String requestUrl = call.request().url().toString();

        Log.d("Request URL", requestUrl);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

                if (response.isSuccessful()) {
                    cardHotel2Adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("call", t.toString());
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
