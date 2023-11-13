package com.example.mobdev_nhom7.models.responseObj.room;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomItem {

    @SerializedName("room_id")
    @Expose
    private String roomId;

    @SerializedName("hotel_id")
    @Expose
    private String hotelId;
    @SerializedName("star")
    @Expose
    private String star;

    @SerializedName("price_per_night")
    @Expose
    private String pricePerNight;

    public String getRoomId() {
        return roomId;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(String pricePerNight) {
        this.pricePerNight = pricePerNight;
    }
}
