package com.example.demo.model;

public class Payment {

    private String paymentId;
    private String userId;
    private String vehicleId;
    private String rentalType;   // Daily or Weekly
    private int rentalDays;
    private double ratePerDay;
    private double totalAmount;
    private String status;       // Paid, Pending, Refunded
    private String paymentDate;

    public Payment() {}

    public Payment(String paymentId, String userId, String vehicleId,
                   String rentalType, int rentalDays, double ratePerDay,
                   double totalAmount, String status, String paymentDate) {
        this.paymentId   = paymentId;
        this.userId      = userId;
        this.vehicleId   = vehicleId;
        this.rentalType  = rentalType;
        this.rentalDays  = rentalDays;
        this.ratePerDay  = ratePerDay;
        this.totalAmount = totalAmount;
        this.status      = status;
        this.paymentDate = paymentDate;
    }

    // Polymorphism: fee calculation differs for Daily vs Weekly
    public double calculateFee() {
        if ("Weekly".equalsIgnoreCase(rentalType)) {
            int weeks = rentalDays / 7;
            int extraDays = rentalDays % 7;
            return (weeks * ratePerDay * 7 * 0.9) + (extraDays * ratePerDay);
        }
        return rentalDays * ratePerDay;
    }

    public String toFileString() {
        return paymentId + "," + userId + "," + vehicleId + "," +
                rentalType + "," + rentalDays + "," + ratePerDay + "," +
                totalAmount + "," + status + "," + paymentDate;
    }

    public static Payment fromFileString(String line) {
        String[] p = line.split(",");
        return new Payment(p[0], p[1], p[2], p[3],
                Integer.parseInt(p[4]),
                Double.parseDouble(p[5]),
                Double.parseDouble(p[6]),
                p[7], p[8]);
    }

    public String getPaymentId()                   { return paymentId; }
    public void setPaymentId(String paymentId)     { this.paymentId = paymentId; }

    public String getUserId()                { return userId; }
    public void setUserId(String userId)     { this.userId = userId; }

    public String getVehicleId()                 { return vehicleId; }
    public void setVehicleId(String vehicleId)   { this.vehicleId = vehicleId; }

    public String getRentalType()                  { return rentalType; }
    public void setRentalType(String rentalType)   { this.rentalType = rentalType; }

    public int getRentalDays()               { return rentalDays; }
    public void setRentalDays(int rentalDays){ this.rentalDays = rentalDays; }

    public double getRatePerDay()                  { return ratePerDay; }
    public void setRatePerDay(double ratePerDay)   { this.ratePerDay = ratePerDay; }

    public double getTotalAmount()                   { return totalAmount; }
    public void setTotalAmount(double totalAmount)   { this.totalAmount = totalAmount; }

    public String getStatus()              { return status; }
    public void setStatus(String status)   { this.status = status; }

    public String getPaymentDate()                   { return paymentDate; }
    public void setPaymentDate(String paymentDate)   { this.paymentDate = paymentDate; }
}