package com.example.mobdev_nhom7.models.bookingRequest;

import java.util.Map;

public class BookingRequest {
    private String user_id;
    private String hotel_id;
    private Map<String, Integer> rooms;
    private String start_date;
    private String end_date;
    private long total_cost;

    public BookingRequest(String user_id, String hotel_id, Map<String, Integer> rooms,
                          String start_date, String end_date, long total_cost) {
        this.user_id = user_id;
        this.hotel_id = hotel_id;
        this.rooms = rooms;
        this.start_date = start_date;
        this.end_date = end_date;
        this.total_cost = total_cost;
    }

    public String getHotelId() {
        return hotel_id;
    }

    public void setHotelId(String hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Map<String, Integer> getRooms() {
        return rooms;
    }

    public void setRooms(Map<String, Integer> rooms) {
        this.rooms = rooms;
    }

    public String getStartDate() {
        return start_date;
    }

    public void setStartDate(String start_date) {
        this.start_date = start_date;
    }

    public String getEndDate() {
        return end_date;
    }

    public void setEndDate(String end_date) {
        this.end_date = end_date;
    }

    public long getTotalCost() {
        return total_cost;
    }

    public void setTotalCost(long total_cost) {
        this.total_cost = total_cost;
    }


}
