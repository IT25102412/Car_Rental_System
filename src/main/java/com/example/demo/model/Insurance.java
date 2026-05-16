package com.example.demo.model;

public class Insurance {

    private String insuranceId;
    private String vehicleId;
    private String userId;
    private String policyType;   // Basic, Full
    private String startDate;
    private String endDate;
    private double premium;
    private String status;       // Active, Expired, Claimed

    public Insurance() {}

    public Insurance(String insuranceId, String vehicleId, String userId,
                     String policyType, String startDate, String endDate,
                     double premium, String status) {
        this.insuranceId = insuranceId;
        this.vehicleId   = vehicleId;
        this.userId      = userId;
        this.policyType  = policyType;
        this.startDate   = startDate;
        this.endDate     = endDate;
        this.premium     = premium;
        this.status      = status;
    }

    // Check if policy covers a given date
    public boolean isActiveOn(String date) {
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
    }

    public String toFileString() {
        return insuranceId + "," + vehicleId + "," + userId + "," +
                policyType + "," + startDate + "," + endDate + "," +
                premium + "," + status;
    }

    public static Insurance fromFileString(String line) {
        String[] p = line.split(",");
        return new Insurance(p[0], p[1], p[2], p[3], p[4], p[5],
                Double.parseDouble(p[6]), p[7]);
    }

    public String getInsuranceId()                   { return insuranceId; }
    public void setInsuranceId(String insuranceId)   { this.insuranceId = insuranceId; }

    public String getVehicleId()                 { return vehicleId; }
    public void setVehicleId(String vehicleId)   { this.vehicleId = vehicleId; }

    public String getUserId()                { return userId; }
    public void setUserId(String userId)     { this.userId = userId; }

    public String getPolicyType()                  { return policyType; }
    public void setPolicyType(String policyType)   { this.policyType = policyType; }

    public String getStartDate()                 { return startDate; }
    public void setStartDate(String startDate)   { this.startDate = startDate; }

    public String getEndDate()               { return endDate; }
    public void setEndDate(String endDate)   { this.endDate = endDate; }

    public double getPremium()               { return premium; }
    public void setPremium(double premium)   { this.premium = premium; }

    public String getStatus()              { return status; }
    public void setStatus(String status)   { this.status = status; }
}