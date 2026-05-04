package com.example.demo.model;

public class BasicInsurance extends Insurance {

    public BasicInsurance() {}

    public BasicInsurance(String insuranceId, String vehicleId, String userId,
                          String startDate, String endDate) {
        super(insuranceId, vehicleId, userId, "Basic",
                startDate, endDate, 50.0, "Active");
    }

    public String getCoverageDetails() {
        return "Third-party liability only";
    }
}