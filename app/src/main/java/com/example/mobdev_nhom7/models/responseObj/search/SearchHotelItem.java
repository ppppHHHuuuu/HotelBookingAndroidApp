package com.example.mobdev_nhom7.models.responseObj.search;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.net.URL;

public class SearchHotelItem {
    @SerializedName("star")
    @Expose
    private String star;
    @SerializedName("hotel_id")
    @Expose
    private String hotelId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("score")
    @Expose
    private Double score;
    @SerializedName("imageURL")
    @Expose
    private String imageURL;
    @SerializedName("distanceFromCenter")
    @Expose
    private String distanceFromCenter;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("number_of_judges")
    @Expose
    private String numberOfJudges;

    public String getPositionFromCenter() {
        return distanceFromCenter;
    }
    public void setPositionFromCenter(String positionFromCenter) {
        this.distanceFromCenter = positionFromCenter;
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


    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumberOfJudges() {
        return numberOfJudges;
    }

    public void setNumberOfJudges(String numberOfJudges) {
        this.numberOfJudges = numberOfJudges;
    }
}
