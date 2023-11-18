package com.example.mobdev_nhom7.models.responseObj.cityName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityNameResponseData {
    @SerializedName("data")
    @Expose
    private List<CityName> data;
    public List<CityName> getData() {return data;}
    public void setData(List<CityName> data) {this.data = data;}
}
