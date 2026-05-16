package com.example.demo.model;

public class User {

    private String userId;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String nicOrPassport;
    private String userType;
    private String securityQuestion;
    private String securityAnswer;

    public User() {}

    public User(String userId, String username, String password,
                String fullName, String email, String phone,
                String nicOrPassport, String userType,
                String securityQuestion, String securityAnswer) {
        this.userId           = userId;
        this.username         = username;
        this.password         = password;
        this.fullName         = fullName;
        this.email            = email;
        this.phone            = phone;
        this.nicOrPassport    = nicOrPassport;
        this.userType         = userType;
        this.securityQuestion = securityQuestion;
        this.securityAnswer   = securityAnswer;
    }

    public String toFileString() {
        return userId + "," + username + "," + password + "," +
                fullName + "," + email + "," + phone + "," +
                nicOrPassport + "," + userType + "," +
                securityQuestion + "," + securityAnswer;
    }

    public static User fromFileString(String line) {
        String[] p = line.split(",", 10);
        String question = p.length > 8 ? p[8] : "What was the name of your first pet?";
        String answer   = p.length > 9 ? p[9] : "default";
        return new User(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], question, answer);
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

    public String getNicOrPassport()                     { return nicOrPassport; }
    public void setNicOrPassport(String nicOrPassport)   { this.nicOrPassport = nicOrPassport; }

    public String getUserType()                { return userType; }
    public void setUserType(String userType)   { this.userType = userType; }

    public String getSecurityQuestion()                      { return securityQuestion; }
    public void setSecurityQuestion(String securityQuestion) { this.securityQuestion = securityQuestion; }

    public String getSecurityAnswer()                    { return securityAnswer; }
    public void setSecurityAnswer(String securityAnswer) { this.securityAnswer = securityAnswer; }
}