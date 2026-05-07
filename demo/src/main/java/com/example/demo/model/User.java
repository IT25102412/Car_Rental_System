// Member 02 - User and Driver Management | IT25103032

package com.example.demo.model;

public class User {

    private String userId;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String licenseNumber;
    private String userType;

    public User() {}

    public User(String userId, String username, String password,
                String fullName, String email, String phone,
                String licenseNumber, String userType) {
        this.userId        = userId;
        this.username      = username;
        this.password      = password;
        this.fullName      = fullName;
        this.email         = email;
        this.phone         = phone;
        this.licenseNumber = licenseNumber;
        this.userType      = userType;
    }

    public String toFileString() {
        return userId + "," + username + "," + password + "," +
                fullName + "," + email + "," + phone + "," +
                licenseNumber + "," + userType;
    }

    public static User fromFileString(String line) {
        String[] p = line.split(",");
        return new User(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7]);
    }

    public String getUserId()                  { return userId; }
    public void setUserId(String userId)       { this.userId = userId; }

    public String getUsername()                { return username; }
    public void setUsername(String username)   { this.username = username; }

    public String getPassword()                { return password; }
    public void setPassword(String password)   { this.password = password; }

    public String getFullName()                { return fullName; }
    public void setFullName(String fullName)   { this.fullName = fullName; }

    public String getEmail()               { return email; }
    public void setEmail(String email)     { this.email = email; }

    public String getPhone()               { return phone; }
    public void setPhone(String phone)     { this.phone = phone; }

    public String getLicenseNumber()                     { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber)   { this.licenseNumber = licenseNumber; }

    public String getUserType()                { return userType; }
    public void setUserType(String userType)   { this.userType = userType; }
}