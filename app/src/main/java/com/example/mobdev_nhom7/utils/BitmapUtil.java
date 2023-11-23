package com.example.mobdev_nhom7.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.URL;

public class BitmapUtil {
    public static Bitmap urlToBitmapConverter(String imageUrl) {
        Bitmap hotelImage;
        try {
            URL url = new URL(imageUrl);
            hotelImage = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return hotelImage;
    }
}
