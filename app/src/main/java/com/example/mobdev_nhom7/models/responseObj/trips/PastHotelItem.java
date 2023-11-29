package com.example.mobdev_nhom7.models.responseObj.trips;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PastHotelItem {
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("id")
    @Expose
    private String reservationID;

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    @SerializedName("hotel_id")
    @Expose
    private String hotel_id;
    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    @SerializedName("image_url")
    @Expose
    private String image_url;
    @SerializedName("hotel_name")
    @Expose
    private String name;
    @SerializedName("start_date")
    @Expose
    private String start_date;
    @SerializedName("end_date")
    @Expose
    private String end_date;
    @SerializedName("comment")
    @Expose
    private String comment;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @SerializedName("total_cost")
    @Expose
    private String amount;


    public String getId() {
        return user_id;
    }

    public void setId(String id) {
        this.user_id = user_id;
    }

    public String getImageURL() {
        return image_url;
    }

    public void setImageURL(String image_url) {
        this.image_url = image_url;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
