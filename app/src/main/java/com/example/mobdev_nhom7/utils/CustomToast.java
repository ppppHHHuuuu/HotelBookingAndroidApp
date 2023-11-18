package com.example.mobdev_nhom7.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class CustomToast {
    public static void makeText(Context context, String error, int length) {
        Log.d("Error", error);
        if (Constants.errorMessages.get(error) != null) {
            Toast.makeText(context, Constants.errorMessages.get(error), length).show();
        } else {
            Toast.makeText(context,"Đã có lỗi không xác định", length).show();
        }
    }
}
