package com.example.mobdev_nhom7;

import android.content.Context;
import android.telecom.Call;

import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelResponseData;
import com.example.mobdev_nhom7.remote.RequestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatPhong {
    public void vaoTrangDatPhong(Context context, String id) {
        List<String> values = new ArrayList<>();
        values.add(id);
        Object searchHotelItem = RequestService.request(context, 2, values).get(0);
    }

    public void datPhong(Context context, String user_id, String hotel_id, String room_1_number, String room_2_number, String start_date, String end_date) {
        List<String> values = new ArrayList<>();
        values.add(user_id);
        values.add(hotel_id);
        Object message = RequestService.request(context, 3, values);
    }
}
