package com.example.mobdev_nhom7.models.hotel;

import com.example.mobdev_nhom7.models.location.LocationModel;
import com.example.mobdev_nhom7.models.responseObj.room.RoomItem;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HotelItem {
    @SerializedName("hotel_id")
    @Expose
    private String hotelId;


    @SerializedName("review")
    @Expose
    private String review;

    @SerializedName("location")
    @Expose
    private LocationModel location;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("contact")
    @Expose
    private String contact;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
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

    public List<String> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<String> roomList) {
        this.roomList = roomList;
    }

    @SerializedName("alert")
    @Expose
    private String alert;

    @SerializedName("room")
    @Expose
    private List<String> roomList;
}
