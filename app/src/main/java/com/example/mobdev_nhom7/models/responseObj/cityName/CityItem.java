package com.example.mobdev_nhom7.models.responseObj.cityName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityItem {
    @SerializedName("id")
    @Expose
    private String cityId;
    @SerializedName("name")
    @Expose
    private String cityName;
    @SerializedName("country")
    @Expose
    private String country;

    public String getCountry() {
        return "Vietnam";
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
