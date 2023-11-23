package com.example.mobdev_nhom7.models.responseObj.hotelName;

import com.example.mobdev_nhom7.models.responseObj.hotel.HotelItem;
import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HotelNameResponseData {
    @SerializedName("data")
    @Expose
    private ArrayList<SearchHotelItem> data;
    public ArrayList<SearchHotelItem> getData() {return data;}
    public void setData(ArrayList<SearchHotelItem> data) {this.data = data;}
}
