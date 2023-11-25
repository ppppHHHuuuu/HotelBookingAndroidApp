package com.example.mobdev_nhom7.models.responseObj.cityDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityDetail {

    @SerializedName("restaurants")
    @Expose
    private List<Restaurant> restaurants;

    @SerializedName("")
    @Expose
    private List<Todo> todos;

    @SerializedName("transportations")
    @Expose
    private List<Transportation> transportations;

    @SerializedName("alerts")
    @Expose
    private List<Alert> alerts;


//     Getter và Setter cho thuộc tính 'restaurants'
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

//     Getter và Setter cho thuộc tính 'todos'
    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

//     Getter và Setter cho thuộc tính 'transportations'
    public List<Transportation> getTransportations() {
        return transportations;
    }

    public void setTransportations(List<Transportation> transportations) {
        this.transportations = transportations;
    }

    // Getter và Setter cho thuộc tính 'alerts'
    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }
}
