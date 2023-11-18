package com.example.mobdev_nhom7.models.responseObj.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchCityResponseData {
    @SerializedName("data")
    @Expose
    private List<SearchCityItem> data;
    public List<SearchCityItem> getData() {
        return data;
    }
    public void setData(List<SearchCityItem> data) {
        this.data = data;
    }
}
