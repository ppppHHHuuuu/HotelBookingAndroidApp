package com.example.mobdev_nhom7.activity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobdev_nhom7.R;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class ViewHotel extends Activity {
    private RecyclerView roomTypeRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_hotel);
//        View price_button1 = findViewById(R.id.button1);
//        TextView roomName = (TextView) price_button1.findViewById(R.id.room_name);
//        TextView roomPrice = (TextView) price_button1.findViewById(R.id.room_price);
//        TextView roomPersons = (TextView) price_button1.findViewById(R.id.room_persons);
//        price_button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                chooseRoomNumberDialog(roomName.getText().toString(), Integer.parseInt(roomPrice.getText().toString()));
//            }
//        });
        roomTypeRV = findViewById(R.id.room_type_RV);
    }

    public void chooseRoomNumberDialog(String name, Integer price) {


        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.room_option_dialog);

        Button confirmBtn = dialog.findViewById(R.id.confirm_button);
        TextView roomName = dialog.findViewById(R.id.title);
        TextView numberOfRooms = dialog.findViewById(R.id.picked_room_num);
        ImageView minusBtn = dialog.findViewById(R.id.minusButton);
        ImageView plusBtn = dialog.findViewById(R.id.plusButton);
        TextView totalRoomPrice = dialog.findViewById(R.id.totalRoomPrice);

        roomName.setText(name);
        Window window = dialog.getWindow();

        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.BOTTOM;

        window.setAttributes(windowAttributes);

        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Decrease the number of rooms
                int currentRooms = Integer.parseInt(numberOfRooms.getText().toString());
                if (currentRooms > 0) {
                    currentRooms--;
                    numberOfRooms.setText(String.valueOf(currentRooms));
                    totalRoomPrice.setText(String.valueOf("VND " + decimalFormat.format(price * currentRooms)));
                }
            }
        });

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Increase the number of rooms
                int currentRooms = Integer.parseInt(numberOfRooms.getText().toString());
                currentRooms++;
                numberOfRooms.setText(String.valueOf(currentRooms));
                totalRoomPrice.setText(String.valueOf("VND " + decimalFormat.format(price * currentRooms)));
            }
        });
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        dialog.show();
    }
}