package com.example.mobdev_nhom7.models.responseObj.hotel;

import com.example.mobdev_nhom7.models.responseObj.amentinies.Amentinies;
import com.example.mobdev_nhom7.models.responseObj.ratings.RatingItem;
import com.example.mobdev_nhom7.models.responseObj.room.RoomItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class HotelItem {
    @SerializedName("hotel_id")
    @Expose
    private String hotelId;

    @SerializedName("imageURL")
    @Expose
    private ArrayList<String> imageURL;

    @SerializedName("amenities")
    @Expose
    private Amentinies amentinies;
    @SerializedName("ratings")
    @Expose
    private RatingItem ratingItem;

    @SerializedName("address")
    @Expose
    private String location;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("contact")
    @Expose
    private String contact;

    @SerializedName("alert")
    @Expose
    private String alert;

    @SerializedName("rooms")
    @Expose
    private Map<String, RoomItem> rooms;
    public  ArrayList<String> getImageURL() {
        return imageURL;
    }
    public Amentinies getAmentinies() {
        return amentinies;
    }

    public void setAmentinies(Amentinies amentinies) {
        this.amentinies = amentinies;
    }

    public RatingItem getRatingItem() {
        return ratingItem;
    }

    public void setRatingItem(RatingItem ratingItem) {
        this.ratingItem = ratingItem;
    }
    public void setImageURL(ArrayList<String> imageURL) {
        this.imageURL = imageURL;
    }
    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public RatingItem getRating() {
        return ratingItem;
    }

    public void setRating(RatingItem review) {
        this.ratingItem = review;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public Map<String, RoomItem> getRooms() {
        return rooms;
    }

    public void setRooms(Map<String, RoomItem> rooms) {
        this.rooms = rooms;
    }
}
