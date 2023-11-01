package com.example.mobdev_nhom7;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SettingsFragment extends Fragment {

    private ImageView profilePic;
    Bitmap results, maskBitmap;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        profilePic = view.findViewById(R.id.pfp);

        Bitmap finalMasking = maskingProcess();
        return view;
    }

    private Bitmap maskingProcess() {
        try {
            Bitmap original, mask;
            original = BitmapFactory.decodeResource(getResources(), R.drawable.demo_pfp);
            mask = BitmapFactory.decodeResource(getResources(), R.drawable.circle_mask);

            if (original != null) {
                int width = original.getWidth();
                int height = original.getHeight();
                results = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                maskBitmap = Bitmap.createScaledBitmap(mask, width, height, true);

                Canvas canvas = new Canvas(results);
                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

                canvas.drawBitmap(original, 0, 0, null);
                canvas.drawBitmap(maskBitmap,0,0,paint);
                paint.setXfermode(null);
                paint.setStyle(Paint.Style.STROKE);
            }
        } catch (OutOfMemoryError outOfMemoryError) {
            outOfMemoryError.printStackTrace();
        }

        profilePic.setImageBitmap(results);
        return results;
    }
}