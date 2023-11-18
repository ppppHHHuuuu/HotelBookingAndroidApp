package com.example.mobdev_nhom7.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideConverter {

        public static void loadImageFromUrl(Context context, String url, ImageView imageView) {
            Glide.with(context)
                    .load(url)
                    .into(imageView);
        }

        public static void loadImageFromRes(Context context, Integer imageRes, ImageView imageView) {
            Glide.with(context)
                    .load(imageRes)
                    .into(imageView);
        }

}
