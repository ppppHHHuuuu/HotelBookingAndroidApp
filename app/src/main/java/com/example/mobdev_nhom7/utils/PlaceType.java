package com.example.mobdev_nhom7.utils;

public enum PlaceType {
    RESTAURANT("restaurant"),
    HOTEL("hotel"),
    PARK("park"),
    MUSEUM("museum"),
    CITY("city");

    private final String displayName;

    PlaceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
