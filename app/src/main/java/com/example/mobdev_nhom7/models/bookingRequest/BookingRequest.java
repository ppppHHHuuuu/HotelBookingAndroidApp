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
}
