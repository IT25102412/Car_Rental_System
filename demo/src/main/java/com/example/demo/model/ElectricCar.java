package com.example.demo.model;

public class ElectricCar extends Vehicle {

    private int batteryRange;

    public ElectricCar() {}

    public ElectricCar(String vehicleId, String make, String model,
                       String licensePlate, String status,
                       int mileage, double dailyRate, int batteryRange) {
        super(vehicleId, make, model, licensePlate, "Electric",
                status, mileage, dailyRate);
        this.batteryRange = batteryRange;
    }

    public int getBatteryRange() { return batteryRange; }
    public void setBatteryRange(int batteryRange) { this.batteryRange = batteryRange; }

    @Override
    public String getType() { return "Electric"; }

    @Override
    public String toFileString() {
        return super.toFileString() + "," + batteryRange;
    }
}