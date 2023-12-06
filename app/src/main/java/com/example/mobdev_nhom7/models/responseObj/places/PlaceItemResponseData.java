package com.example.mobdev_nhom7.models.responseObj.places;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceItemResponseData {
    @SerializedName("data")
    @Expose
    private List<PlaceItem> data;
    public List<PlaceItem> getData() {
        return data;
    }
    public void setData(List<PlaceItem> data) {
        this.data = data;
    }
}
