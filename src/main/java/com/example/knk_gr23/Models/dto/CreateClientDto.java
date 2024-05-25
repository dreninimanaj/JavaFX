package com.example.knk_gr23.Models.dto;

public class CreateClientDto {
    private String username;
    private String passwordHash;
    private String email;
    private String role;
    private String salt;
    private String name;
    private String address;
    private String phone;
    private String employmentStatus;
    private double income;
    private String creditHistory;
    private double debtToIncomeRatio;

    // Default constructor
    public CreateClientDto() {
    }

    // Parameterized constructor
    public CreateClientDto(String username, String passwordHash, String email, String role, String salt,
                           String name, String address, String phone, String employmentStatus,
                           double income, String creditHistory, double debtToIncomeRatio) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.role = role;
        this.salt = salt;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.employmentStatus = employmentStatus;
        this.income = income;
        this.creditHistory = creditHistory;
        this.debtToIncomeRatio = debtToIncomeRatio;
        System.out.println("brenda dtose");
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public String getCreditHistory() {
        return creditHistory;
    }

    public void setCreditHistory(String creditHistory) {
        this.creditHistory = creditHistory;
    }

    public double getDebtToIncomeRatio() {
        return debtToIncomeRatio;
    }

    public void setDebtToIncomeRatio(double debtToIncomeRatio) {
        this.debtToIncomeRatio = debtToIncomeRatio;
    }
}
