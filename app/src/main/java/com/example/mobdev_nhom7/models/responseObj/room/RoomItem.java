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
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("capacity")
    @Expose
    private String capacity;
    @SerializedName("price")
    @Expose
    private String pricePerNight;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    private String roomName;
    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
    public String getRoomId() {
        return roomId;
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
