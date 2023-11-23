package com.example.mobdev_nhom7.models.responseObj.cityName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CityItemResponseData {
    @SerializedName("data")
    @Expose
    private ArrayList<CityItem> data;
    public ArrayList<CityItem> getData() {return data;}
    public void setData(ArrayList<CityItem> data) { this.data = data;}
}
