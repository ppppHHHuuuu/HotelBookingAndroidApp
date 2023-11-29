package com.example.mobdev_nhom7.models.requestObj.feedback;

import com.example.mobdev_nhom7.models.responseObj.ratings.RatingItem;
import com.google.gson.annotations.SerializedName;

public class FeedbackRequest {
    @SerializedName("reservation_id")
    private String reservationId;

    @SerializedName("ratings")
    private RatingItem ratings;

    @SerializedName("comment")
    private String comment;

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public RatingItem getRatings() {
        return ratings;
    }

    public void setRatings(RatingItem ratings) {
        this.ratings = ratings;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
// Constructor, getters, and setters...
}
