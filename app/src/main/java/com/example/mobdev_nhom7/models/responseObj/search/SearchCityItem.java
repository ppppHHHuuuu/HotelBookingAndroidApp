package com.example.mobdev_nhom7.models.responseObj.search;

import com.example.mobdev_nhom7.models.responseObj.hotel.HotelItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchCityItem {
    @SerializedName("city_id")
    @Expose
    private String cityId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("country")
    @Expose
    private String location;

    @SerializedName("hotels")
    @Expose
    private List<String> hotels;

    @SerializedName("distance")
    @Expose
    private String distance;
}
