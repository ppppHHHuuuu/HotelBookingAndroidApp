package com.example.mobdev_nhom7.models.responseObj.ratings;

import com.example.mobdev_nhom7.models.responseObj.hotel.HotelItem;
import com.example.mobdev_nhom7.models.responseObj.room.RoomItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Rating {
    @SerializedName("hotel_id")
    @Expose
    private String hotelId;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("cleanliness")
    @Expose
    private List<RoomItem> cleanliness;
    @SerializedName("building")
    @Expose
    private List<RoomItem> building;
    @SerializedName("comfort")
    @Expose
    private List<RoomItem> comfort;
}
