package com.example.mobdev_nhom7.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.security.spec.ECField;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static void ggDriveConverter(String url, ImageView imageView) {
        if (url == null || url == "") {
            Log.d("url", "is empty or null");
            return;
        }
        if (url.startsWith("https://drive.google.com/file/d/")) {
            // Extract the file ID from the input link
            Pattern pattern = Pattern.compile("/file/d/([^/]+)/");
            Matcher matcher = pattern.matcher(url);

            // If a match is found, construct the direct link
            if (matcher.find()) {
                String fileId = matcher.group(1);
                url =  "https://drive.google.com/uc?id=" + fileId;
            }
        }
        try {
            Picasso.get()
                    .load(url)
                    .fit()
                    .into(imageView);
            Log.d("url", url);
        }
        catch(Exception e) {
            Picasso.get()
                    .load("https://drive.google.com/uc?id=1VgY1DsGCSJ6z3u45Lv8RHpiL33JkBYUu")
                    .fit()
                    .into(imageView);
            Log.d("url", "https://drive.google.com/uc?id=1VgY1DsGCSJ6z3u45Lv8RHpiL33JkBYUu");

            Log.d("error", Objects.requireNonNull(e.getMessage()));
        }
    }
}
