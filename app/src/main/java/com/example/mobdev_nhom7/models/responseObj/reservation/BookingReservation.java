package com.example.mobdev_nhom7.models.responseObj.reservation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingReservation {
    @SerializedName("date_from")
    @Expose
    private String dateFrom;
    @SerializedName("date_to")
    @Expose
    private String dateTo;
    @SerializedName("reservation_id")
    @Expose
    private String reservationId;
    @SerializedName("status")
    @Expose
    private String status;

    public String getDateFrom() {
        return dateFrom;
    }
    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }
    public String getDateTo() {
        return dateTo;
    }
    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
    public String getReservationId() {
        return reservationId;
    }
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
