package com.example.mobdev_nhom7.models.responseObj.trips;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActiveHotelItem {
    public String getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(String reservation_id) {
        this.reservation_id = reservation_id;
    }

    @SerializedName("id")
    @Expose
    private String reservation_id;

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    @SerializedName("hotel_id")
    @Expose
    private String hotel_id;

    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("hotel_imageURL")
    @Expose
    private String url;
    @SerializedName("hotel_name")
    @Expose
    private String name;
    @SerializedName("start_date")
    @Expose
    private String start_date;
    @SerializedName("end_date")
    @Expose
    private String end_date;
    @SerializedName("total_cost")
    @Expose
    private String amount;

    public String getuser_id() {
        return user_id;
    }

    public void setuser_id(String id) {
        this.user_id= user_id;
    }

    public String getImageURL() {
        return url;
    }

    public void setImageURL(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return start_date;
    }

    public void setStartDate(String start_date) {
        this.start_date = start_date;
    }

    public String getEndDate() {
        return end_date;
    }

    public void setEndDate(String end_date) {
        this.end_date = end_date;
    }

    public String getAmount() {
        return amount;
    }
}
