package com.example.mobdev_nhom7.utils;

public class AmountConverter {
    public static String calculate(double rate) {
        if (rate > 8.5) {
            return "Very Nice";
        } else if (rate > 7.0) {
            return "Greate";
        } else if (rate > 5.0) {
            return "Acceptable";
        } else {
            return "Suck";
        }
    }
}
