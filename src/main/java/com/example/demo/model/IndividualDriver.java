package com.example.demo.model;

public class IndividualDriver extends User {

    private String address;

    public IndividualDriver() {}

    public IndividualDriver(String userId, String username, String password,
                            String fullName, String email, String phone,
                            String nicOrPassport, String address) {
        super(userId, username, password, fullName, email, phone,
                nicOrPassport, "Individual", "default", "default");
        this.address = address;
    }

    public String getAddress()             { return address; }
    public void setAddress(String address) { this.address = address; }
}