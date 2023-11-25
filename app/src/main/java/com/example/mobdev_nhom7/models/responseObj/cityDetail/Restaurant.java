package com.example.mobdev_nhom7.models.responseObj.cityDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {
    @SerializedName("image")
    @Expose
    private String image;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("rating")
    @Expose
    private int rating;

    @SerializedName("id")
    @Expose
    private String id;

    // Getter cho thuộc tính 'image'
    public String getImage() {
        return image;
    }

    // Setter cho thuộc tính 'image'
    public void setImage(String image) {
        this.image = image;
    }

    // Getter cho thuộc tính 'address'
    public String getAddress() {
        return address;
    }

    // Setter cho thuộc tính 'address'
    public void setAddress(String address) {
        this.address = address;
    }

    // Getter cho thuộc tính 'name'
    public String getName() {
        return name;
    }

    // Setter cho thuộc tính 'name'
    public void setName(String name) {
        this.name = name;
    }

    // Getter cho thuộc tính 'rating'
    public int getRating() {
        return rating;
    }

    // Setter cho thuộc tính 'rating'
    public void setRating(int rating) {
        this.rating = rating;
    }

    // Getter cho thuộc tính 'id'
    public String getId() {
        return id;
    }

    // Setter cho thuộc tính 'id'
    public void setId(String id) {
        this.id = id;
    }
}
