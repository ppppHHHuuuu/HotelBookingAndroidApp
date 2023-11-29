package com.example.mobdev_nhom7.models.responseObj.cityDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transportation {
    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("rating")
    @Expose
    private Integer rating;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("id")
    @Expose
    private String id;
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
