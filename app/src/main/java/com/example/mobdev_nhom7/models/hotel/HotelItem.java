package com.example.mobdev_nhom7.models.hotel;

public class HotelItem {
    private String name;
    private String star;
    private String city;
    private String score;
    private String rate;
    private String judge;
    private Integer image;
    private String distance;
    private String lastBooking;
    private String amount;

    public HotelItem(String name, String star, String city, String score, String rate, String judge, Integer image, String distance, String lastBooking, String amount) {
        this.name = name;
        this.star = star;
        this.city = city;
        this.score = score;
        this.rate = rate;
        this.judge = judge;
        this.image = image;
        this.distance = distance;
        this.lastBooking = lastBooking;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getStar() {
        return star;
    }

    public String getCity() {
        return city;
    }

    public String getScore() {
        return score;
    }

    public String getRate() {
        return rate;
    }

    public String getJudge() {
        return judge;
    }

    public Integer getImage() {
        return image;
    }

    public String getDistance() {
        return distance;
    }

    public String getLastBooking() {
        return lastBooking;
    }

    public String getAmount() {
        return amount;
    }
}
