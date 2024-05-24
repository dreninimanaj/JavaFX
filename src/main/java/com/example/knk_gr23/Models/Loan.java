package com.example.knk_gr23.Models;

import java.math.BigDecimal;
import java.util.Date;

public class Loan {
    private int loan_id;
    private int client_id;
    private BigDecimal loan_amount;
    private BigDecimal interest_rate;
    private Date loan_start_date;
    private Date loan_end_date;
    private String loan_status;

    // Default constructor
    public Loan() {
    }

    // Parameterized constructor
    public Loan(int loan_id, int client_id, BigDecimal loan_amount, BigDecimal interest_rate, Date loan_start_date, Date loan_end_date, String loan_status) {
        this.loan_id = loan_id;
        this.client_id = client_id;
        this.loan_amount = loan_amount;
        this.interest_rate = interest_rate;
        this.loan_start_date = loan_start_date;
        this.loan_end_date = loan_end_date;
        this.loan_status = loan_status;
    }

    // Getters and Setters
    public int getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public BigDecimal getLoan_amount() {
        return loan_amount;
    }

    public void setLoan_amount(BigDecimal loan_amount) {
        this.loan_amount = loan_amount;
    }

    public BigDecimal getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(BigDecimal interest_rate) {
        this.interest_rate = interest_rate;
    }

    public Date getLoan_start_date() {
        return loan_start_date;
    }

    public void setLoan_start_date(Date loan_start_date) {
        this.loan_start_date = loan_start_date;
    }

    public Date getLoan_end_date() {
        return loan_end_date;
    }

    public void setLoan_end_date(Date loan_end_date) {
        this.loan_end_date = loan_end_date;
    }

    public String getLoan_status() {
        return loan_status;
    }

    public void setLoan_status(String loan_status) {
        this.loan_status = loan_status;
    }
}
