package com.example.mobdev_nhom7.models.responseObj.cityName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CityNameResponseData {
    @SerializedName("data")
    @Expose
    private ArrayList<CityName> data;
    public ArrayList<CityName> getData() {return data;}
    public void setData(ArrayList<CityName> data) {this.data = data;}
}
