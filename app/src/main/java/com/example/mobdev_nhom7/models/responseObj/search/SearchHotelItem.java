package com.example.mobdev_nhom7.models.responseObj.search;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.URL;

public class SearchHotelItem {
    @SerializedName("hotel_id")
    @Expose
    private String hotelId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("imageURL")
    @Expose
    private String imageURL;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("positionFromCenter")
    @Expose
    private String positionFromCenter;
    @SerializedName("isLoved")
    @Expose
    private String isLoved;
    @SerializedName("isBooked")
    @Expose
    private String isBooked;
    @SerializedName("isCanceled")
    @Expose
    private String isCanceled;
    @SerializedName("isBooking")
    @Expose
    private String isBooking;

    public String getIsCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(String isCanceled) {
        this.isCanceled = isCanceled;
    }

    public String getIsBooking() {
        return isBooking;
    }

    public void setIsBooking(String isBooking) {
        this.isBooking = isBooking;
    }

    public String getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(String isBooked) {
        this.isBooked = isBooked;
    }

    public String getIsLoved() {
        return isLoved;
    }

    public void setIsLoved(String isLoved) {
        this.isLoved = isLoved;
    }

    public String getPositionFromCenter() {
        return positionFromCenter;
    }

    public void setPositionFromCenter(String positionFromCenter) {
        this.positionFromCenter = positionFromCenter;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @SerializedName("review")
    @Expose
    private String review;

    @SerializedName("distance")
    @Expose
    private String distance;
}
