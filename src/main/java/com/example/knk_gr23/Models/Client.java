package com.example.knk_gr23.Models;

import java.math.BigDecimal;

public class Client {
    private int clientId;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String employmentStatus;
    private BigDecimal income;
    private String creditHistory;
    private BigDecimal debtToIncomeRatio;
    private int usersId;

    // Default constructor
    public Client() {
    }

    // Parameterized constructor
    public Client(int clientId, String name, String address, String phone, String email, 
                  String employmentStatus, BigDecimal income, String creditHistory, 
                  BigDecimal debtToIncomeRatio, int usersId) {
        this.clientId = clientId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.employmentStatus = employmentStatus;
        this.income = income;
        this.creditHistory = creditHistory;
        this.debtToIncomeRatio = debtToIncomeRatio;
        this.usersId = usersId;
    }

    // Getters and Setters
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getCreditHistory() {
        return creditHistory;
    }

    public void setCreditHistory(String creditHistory) {
        this.creditHistory = creditHistory;
    }

    public BigDecimal getDebtToIncomeRatio() {
        return debtToIncomeRatio;
    }

    public void setDebtToIncomeRatio(BigDecimal debtToIncomeRatio) {
        this.debtToIncomeRatio = debtToIncomeRatio;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }


}
