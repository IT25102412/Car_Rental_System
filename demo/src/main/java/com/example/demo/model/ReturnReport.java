// Member 05 - Rental Return and Damage Reports | IT25101250
package com.example.demo.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReturnReport {

    private String returnId;
    private String bookingId;
    private String vehicleId;
    private String userId;
    private String returnDate;
    private String expectedDate;
    private String condition;
    private double lateFine;
    private String memberType;
    private String notes;

    public ReturnReport() {}

    public ReturnReport(String returnId, String bookingId, String vehicleId,
                        String userId, String returnDate, String expectedDate,
                        String condition, double lateFine, String memberType, String notes) {
        this.returnId    = returnId;
        this.bookingId   = bookingId;
        this.vehicleId   = vehicleId;
        this.userId      = userId;
        this.returnDate  = returnDate;
        this.expectedDate = expectedDate;
        this.condition   = condition;
        this.lateFine    = lateFine;
        this.memberType  = memberType;
        this.notes       = notes;
    }

    // Polymorphism: fine differs for Regular vs Premium members
    public double calculateFine() {
        try {
            LocalDate returnD   = LocalDate.parse(returnDate);
            LocalDate expectedD = LocalDate.parse(expectedDate);
            long lateDays = ChronoUnit.DAYS.between(expectedD, returnD);
            if (lateDays <= 0) return 0.0;
            if ("Premium".equalsIgnoreCase(memberType)) {
                return lateDays * 250.0;
            }
            return lateDays * 625.0;
        } catch (Exception e) {
            return 0.0;
        }
    }

    public String toFileString() {
        return returnId + "," + bookingId + "," + vehicleId + "," +
                userId + "," + returnDate + "," + expectedDate + "," +
                condition + "," + lateFine + "," + memberType + "," + notes;
    }

    public static ReturnReport fromFileString(String line) {
        String[] p = line.split(",", 10);
        return new ReturnReport(p[0], p[1], p[2], p[3], p[4], p[5],
                p[6], Double.parseDouble(p[7]), p[8], p[9]);
    }

    public String getReturnId()                  { return returnId; }
    public void setReturnId(String returnId)     { this.returnId = returnId; }

    public String getBookingId()                 { return bookingId; }
    public void setBookingId(String bookingId)   { this.bookingId = bookingId; }

    public String getVehicleId()                 { return vehicleId; }
    public void setVehicleId(String vehicleId)   { this.vehicleId = vehicleId; }

    public String getUserId()                { return userId; }
    public void setUserId(String userId)     { this.userId = userId; }

    public String getReturnDate()                  { return returnDate; }
    public void setReturnDate(String returnDate)   { this.returnDate = returnDate; }

    public String getExpectedDate()                    { return expectedDate; }
    public void setExpectedDate(String expectedDate)   { this.expectedDate = expectedDate; }

    public String getCondition()                 { return condition; }
    public void setCondition(String condition)   { this.condition = condition; }

    public double getLateFine()                { return lateFine; }
    public void setLateFine(double lateFine)   { this.lateFine = lateFine; }

    public String getMemberType()                  { return memberType; }
    public void setMemberType(String memberType)   { this.memberType = memberType; }

    public String getNotes()               { return notes; }
    public void setNotes(String notes)     { this.notes = notes; }
}