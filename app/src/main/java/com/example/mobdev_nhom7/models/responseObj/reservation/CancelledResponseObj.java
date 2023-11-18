package com.example.mobdev_nhom7.models.responseObj.reservation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CancelledResponseObj {
    @SerializedName("data")
    @Expose
    private List<HistoryReservation>  data;
    public List<HistoryReservation> getData() {
        return data;
    }
    public void setData(List<HistoryReservation> data) {
        this.data = data;
    }
}
