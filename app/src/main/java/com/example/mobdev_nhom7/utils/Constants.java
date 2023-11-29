package com.example.mobdev_nhom7.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static Map<String, String> errorMessages = new HashMap<String, String>() {{
        put("A network error (such as timeout, interrupted connection or unreachable host) has occurred.", "No Internet connection");
        put("Given String is empty or null", "You have not entered your password");
        put("An internal error has occurred. [ INVALID_LOGIN_CREDENTIALS ]", "\n" +
                "Wrong password");
        put("The verification code from SMS/TOTP is invalid. Please check and enter the correct verification code again.", "\n" +
                "Wrong authentication code");
    }};

}
