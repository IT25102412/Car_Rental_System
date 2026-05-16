package com.example.demo.model;

public class CorporateClient extends User {

    private String companyName;

    public CorporateClient() {}

    public CorporateClient(String userId, String username, String password,
                           String fullName, String email, String phone,
                           String nicOrPassport, String companyName) {
        super(userId, username, password, fullName, email, phone,
                nicOrPassport, "Corporate", "default", "default");
        this.companyName = companyName;
    }

    public String getCompanyName()                 { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
}