package com.example.mobdev_nhom7.models.responseObj.hotelName;

import com.example.mobdev_nhom7.models.responseObj.room.RoomItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelName {
    @SerializedName("hotel_id")
    @Expose
    private String hotelId;
    @SerializedName("hotel_name")
    @Expose
    private String hotelName;
    @SerializedName("city_id")
    @Expose
    private String city_id;
}
