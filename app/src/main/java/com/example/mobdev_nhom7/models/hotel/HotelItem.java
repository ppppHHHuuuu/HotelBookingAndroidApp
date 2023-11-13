package com.example.mobdev_nhom7.models.hotel;

import com.example.mobdev_nhom7.models.responseObj.room.RoomItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelItem {
    @SerializedName("hotel_id")
    @Expose
    private String hotelId;


    @SerializedName("review")
    @Expose
    private String review;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("contact")
    @Expose
    private String contact;

    @SerializedName("alert")
    @Expose
    private String alert;

    @SerializedName("room")
    @Expose
    private List<String> roomList;
}
