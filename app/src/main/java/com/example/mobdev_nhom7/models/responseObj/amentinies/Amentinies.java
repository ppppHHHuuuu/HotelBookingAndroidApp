package com.example.mobdev_nhom7.models.responseObj.amentinies;

public class Amentinies {
    private boolean wifiInLobby;
    private boolean spa;
    private boolean wifiInRoom;
    private boolean pool;
    private boolean telephone;
    private boolean pet;

    public boolean isWifiInLobby() {
        return wifiInLobby;
    }

    public void setWifiInLobby(boolean wifiInLobby) {
        this.wifiInLobby = wifiInLobby;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isWifiInRoom() {
        return wifiInRoom;
    }

    public void setWifiInRoom(boolean wifiInRoom) {
        this.wifiInRoom = wifiInRoom;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isTelephone() {
        return telephone;
    }

    public void setTelephone(boolean telephone) {
        this.telephone = telephone;
    }

    public boolean isPet() {
        return pet;
    }

    public void setPet(boolean pet) {
        this.pet = pet;
    }
}
