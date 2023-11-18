package com.example.mobdev_nhom7.models.responseObj.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchHotelResponseData {
    @SerializedName("data")
    @Expose
    private List<SearchHotelItem> data;
    public List<SearchHotelItem> getData() {
        return data;
    }
    public void setData(List<SearchHotelItem> data) {
        this.data = data;
    }
}
