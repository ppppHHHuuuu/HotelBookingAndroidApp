package com.example.mobdev_nhom7.models.responseObj.trips;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoryHotelItem {
    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("url")
    @Expose
    private String image_url;
    @SerializedName("name")
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
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("is_cancelled")
    @Expose
    private Boolean is_cancelled;
    public Boolean getIsCancelled() {
        return is_cancelled;
    }

    public void setIsCancelled(Boolean is_cancelled) {
        this.is_cancelled = is_cancelled;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }




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
