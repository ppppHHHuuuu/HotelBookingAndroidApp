package com.example.mobdev_nhom7.models.responseObj.hotel;

import com.example.mobdev_nhom7.models.responseObj.search.SearchHotelItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelResponseObj {
    @SerializedName("data")
    @Expose
    private List<HotelItem> data;
    public List<HotelItem> getData() {
        return data;
    }
    public void setData(List<HotelItem> data) {
        this.data = data;
    }
}
