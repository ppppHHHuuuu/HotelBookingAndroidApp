package com.example.mobdev_nhom7.models.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoomType {
    private String roomTypeName;

    private int roomTypeCapacity;

    private int roomTypePrice;

    private int roomTypeQuantity;

    // Getter for roomTypeName
    public String getRoomTypeName() {
        return roomTypeName;
    }

    // Setter for roomTypeName
    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    // Getter for roomTypeCapacity
    public int getRoomTypeCapacity() {
        return roomTypeCapacity;
    }

    // Setter for roomTypeCapacity
    public void setRoomTypeCapacity(int roomTypeCapacity) {
        this.roomTypeCapacity = roomTypeCapacity;
    }

    // Getter for roomTypePrice
    public int getRoomTypePrice() {
        return roomTypePrice;
    }

    // Setter for roomTypePrice
    public void setRoomTypePrice(int roomTypePrice) {
        this.roomTypePrice = roomTypePrice;
    }

    // Getter for roomTypeQuantity
    public int getRoomTypeQuantity() {
        return roomTypeQuantity;
    }

    // Setter for roomTypeQuantity
    public void setRoomTypeQuantity(int roomTypeQuantity) {
        this.roomTypeQuantity = roomTypeQuantity;
    }


}
