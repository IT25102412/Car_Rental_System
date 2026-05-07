package com.example.demo.model;

public class Booking {

    private String bookingId;
    private String userId;
    private String vehicleId;
    private String startDate;
    private String endDate;
    private String status;  // Confirmed, Cancelled, Completed

    public Booking() {}

    public Booking(String bookingId, String userId, String vehicleId,
                   String startDate, String endDate, String status) {
        this.bookingId = bookingId;
        this.userId    = userId;
        this.vehicleId = vehicleId;
        this.startDate = startDate;
        this.endDate   = endDate;
        this.status    = status;
    }

    // Abstraction: availability check logic hidden from UI
    public boolean isAvailable(String requestedStart, String requestedEnd) {
        return requestedEnd.compareTo(this.startDate) < 0 ||
                requestedStart.compareTo(this.endDate) > 0;
    }

    public String toFileString() {
        return bookingId + "," + userId + "," + vehicleId + "," +
                startDate + "," + endDate + "," + status;
    }

    public static Booking fromFileString(String line) {
        String[] p = line.split(",");
        return new Booking(p[0], p[1], p[2], p[3], p[4], p[5]);
    }

    public String getBookingId()                   { return bookingId; }
    public void setBookingId(String bookingId)     { this.bookingId = bookingId; }

    public String getUserId()                { return userId; }
    public void setUserId(String userId)     { this.userId = userId; }

    public String getVehicleId()                 { return vehicleId; }
    public void setVehicleId(String vehicleId)   { this.vehicleId = vehicleId; }

    public String getStartDate()                 { return startDate; }
    public void setStartDate(String startDate)   { this.startDate = startDate; }

    public String getEndDate()               { return endDate; }
    public void setEndDate(String endDate)   { this.endDate = endDate; }

    public String getStatus()              { return status; }
    public void setStatus(String status)   { this.status = status; }

}