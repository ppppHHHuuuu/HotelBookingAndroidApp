package com.example.mobdev_nhom7.models.responseObj.hotelName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelNameResponseData {
    @SerializedName("data")
    @Expose
    private List<HotelName> data;
    public List<HotelName> getData() {return data;}
    public void setData(List<HotelName> data) {this.data = data;}
}
