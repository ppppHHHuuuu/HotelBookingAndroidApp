package com.example.mobdev_nhom7.models.responseObj.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentItem {
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("created_date")
    @Expose
    private String created_date;
    @SerializedName("hotel_id")
    @Expose
    private String hotel_id;
    @SerializedName("feedback")
    @Expose
    private FeedbackItem feedbackItem;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public FeedbackItem getFeedbackItem() {
        return feedbackItem;
    }

    public void setFeedbackItem(FeedbackItem feedbackItem) {
        this.feedbackItem = feedbackItem;
    }
}
