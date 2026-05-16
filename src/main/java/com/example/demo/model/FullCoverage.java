package com.example.demo.model;

public class FullCoverage extends Insurance {

    public FullCoverage() {}

    public FullCoverage(String insuranceId, String vehicleId, String userId,
                        String startDate, String endDate) {
        super(insuranceId, vehicleId, userId, "Full",
                startDate, endDate, 150.0, "Active");
    }

    public String getCoverageDetails() {
        return "Comprehensive — theft, damage, liability";
    }
}