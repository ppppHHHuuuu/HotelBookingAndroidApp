package com.example.mobdev_nhom7.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static Map<String, String> errorMessages = new HashMap<String, String>() {{
        put("A network error (such as timeout, interrupted connection or unreachable host) has occurred.", "Không có kết nối Internet");
        put("Given String is empty or null", "Bạn chưa nhập mật khẩu");
        put("An internal error has occurred. [ INVALID_LOGIN_CREDENTIALS ]", "Sai mật khẩu");
        put("The verification code from SMS/TOTP is invalid. Please check and enter the correct verification code again.", "Sai mã xác thực");
    }};

}
