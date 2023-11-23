package com.example.mobdev_nhom7.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DateTimeUtil {

    public static boolean isBeforeCurrentDate (String date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Parse the end date string into a Date object
            Date endDate = dateFormat.parse(date);

            // Get the current date and time
            Date currentDate = new Date();

            // Compare the dates
            if (endDate.before(currentDate)) {
                return true;
            } else if (endDate.after(currentDate)) {
                return false;
            } else {
                return false;
            }
        } catch (ParseException e) {
            // Handle the exception (e.g., invalid date format)
            e.printStackTrace();
        }
        return false;
    }
    public static String standardlizeDatetime(String input) {
        String[] input_formats = {
        "%Y-%m-%d %H:%M:%S",
                "%Y-%m-%d %H:%M",
                "%Y-%m-%d %H",
                "%Y-%m-%d",
                "%H:%M:%S",
                "%H:%M"};

        for (String format : input_formats) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false);

            try {
                // Try to parse the input using the current format
                Date date = dateFormat.parse(input);

                // If successful, convert it to the desired output format
                SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy ss:mm:HH");
                assert date != null;
                return outputFormat.format(date);
            } catch (ParseException e) {
                // Parsing failed, continue to the next format
                continue;
            }
        }
        throw new IllegalArgumentException("Input datetime format not recognized");

    }
}
