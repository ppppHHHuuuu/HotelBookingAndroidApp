package com.example.mobdev_nhom7.models.responseObj.room;

import com.example.mobdev_nhom7.models.responseObj.reservation.HistoryReservation;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RoomResponseData {

    @SerializedName("data")
    @Expose
    private List<RoomItem> data;
    public List<RoomItem> getData() {
        return data;
    }
    public void setData(List<RoomItem> data) {
        this.data = data;
    }
}
