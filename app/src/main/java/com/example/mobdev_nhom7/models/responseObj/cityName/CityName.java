package com.example.mobdev_nhom7.models.responseObj.cityName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityName {
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("city_name")
    @Expose
    private String cityName;
}
