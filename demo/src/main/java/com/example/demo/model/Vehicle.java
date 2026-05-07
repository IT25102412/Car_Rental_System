package com.example.demo.model;

public class Vehicle {

    private String vehicleId;
    private String make;
    private String model;
    private String licensePlate;
    private String category;
    private String status;
    private int mileage;
    private double dailyRate;

    public Vehicle() {}

    public Vehicle(String vehicleId, String make, String model,
                   String licensePlate, String category,
                   String status, int mileage, double dailyRate) {
        this.vehicleId    = vehicleId;
        this.make         = make;
        this.model        = model;
        this.licensePlate = licensePlate;
        this.category     = category;
        this.status       = status;
        this.mileage      = mileage;
        this.dailyRate    = dailyRate;
    }

    // Convert object to one line for saving to cars.txt
    public String toFileString() {
        return vehicleId + "," + make + "," + model + "," +
                licensePlate + "," + category + "," +
                status + "," + mileage + "," + dailyRate;
    }

    // Convert one line from cars.txt back to a Vehicle object
    public static Vehicle fromFileString(String line) {
        String[] p = line.split(",");
        return new Vehicle(p[0], p[1], p[2], p[3], p[4], p[5],
                Integer.parseInt(p[6]),
                Double.parseDouble(p[7]));
    }

    // Getters and Setters
    public String getVehicleId()               { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public String getMake()            { return make; }
    public void setMake(String make)   { this.make = make; }

    public String getModel()             { return model; }
    public void setModel(String model)   { this.model = model; }

    public String getLicensePlate()                    { return licensePlate; }
    public void setLicensePlate(String licensePlate)   { this.licensePlate = licensePlate; }

    public String getCategory()                { return category; }
    public void setCategory(String category)   { this.category = category; }

    public String getStatus()              { return status; }
    public void setStatus(String status)   { this.status = status; }

    public int getMileage()              { return mileage; }
    public void setMileage(int mileage)  { this.mileage = mileage; }

    public double getDailyRate()               { return dailyRate; }
    public void setDailyRate(double dailyRate) { this.dailyRate = dailyRate; }

    public String getType() { return "Vehicle"; }
}