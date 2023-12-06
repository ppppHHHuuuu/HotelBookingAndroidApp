package com.example.mobdev_nhom7.models.responseObj.hotelDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelDetail {
    @SerializedName("amenities")
    @Expose
    private Amenity amenity;

    @SerializedName("city_id")
    @Expose
    private String cityId;

    @SerializedName("contact")
    @Expose
    private String contact;

    @SerializedName("distance_from_center")
    @Expose
    private String distance;

    @SerializedName("imageURL")
    @Expose
    private List<String> imageURLs;

    @SerializedName("name")
    @Expose
    private String name;

}
