package com.example.mobdev_nhom7.models.responseObj.search;

import com.example.mobdev_nhom7.models.responseObj.image.ImageItem;
import com.example.mobdev_nhom7.models.responseObj.ratings.RatingItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchHotelItem {
    @SerializedName("name")//check
    @Expose
    private String name;
    @SerializedName("star")//check
    @Expose
    private Float star;
    @SerializedName("id")//check
    @Expose
    private String hotelId;
    @SerializedName("ratings")//ratings.value
    @Expose
    private RatingItem score;
    @SerializedName("imageURL")//check
    @Expose
    private List<String> imageURL;
    @SerializedName("distance_from_center")//check
    @Expose
    private String distanceFromCenter;
    @SerializedName("city_name")
    @Expose
    private String city;
    @SerializedName("min_price")//check
    @Expose
    private String amount;
//    @SerializedName("is")//check
//    @Expose
//    private String amount;
    public String getDistanceFromCenter() {
        return distanceFromCenter;
    }

    public void setDistanceFromCenter(String distanceFromCenter) {
        this.distanceFromCenter = distanceFromCenter;
    }
    public RatingItem getScore() {
        return score;
    }

    public void setScore(RatingItem score) {
        this.score = score;
    }

    public String getPositionFromCenter() {
        return distanceFromCenter;
    }
    public void setPositionFromCenter(String positionFromCenter) {
        this.distanceFromCenter = positionFromCenter;
    }

    public List<String> getImageItemURL() {
        return imageURL;
    }

    public void setImageURL(List<String> imageURL) {
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


    public Float getStar() {
        return star;
    }

    public void setStar(Float star) {
        this.star = star;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
