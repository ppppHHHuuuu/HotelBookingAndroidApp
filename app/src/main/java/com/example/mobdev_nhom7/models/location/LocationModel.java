package com.example.mobdev_nhom7.models.location;

public class LocationModel {
    private String address;
    private String city;
    private String postalCode;

    public LocationModel(String address, String city, String postalCode) {
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
    }

    // Getters
    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
