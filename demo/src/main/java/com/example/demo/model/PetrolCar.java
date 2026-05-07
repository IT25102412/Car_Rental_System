package com.example.demo.model;

public class PetrolCar extends Vehicle {

    private double fuelEfficiency;

    public PetrolCar() {}

    public PetrolCar(String vehicleId, String make, String model,
                     String licensePlate, String category,
                     String status, int mileage,
                     double dailyRate, double fuelEfficiency) {
        super(vehicleId, make, model, licensePlate, category,
                status, mileage, dailyRate);
        this.fuelEfficiency = fuelEfficiency;
    }

    public double getFuelEfficiency() { return fuelEfficiency; }
    public void setFuelEfficiency(double fuelEfficiency) { this.fuelEfficiency = fuelEfficiency; }

    @Override
    public String getType() { return "Petrol"; }

    @Override
    public String toFileString() {
        return super.toFileString() + "," + fuelEfficiency;
    }
}