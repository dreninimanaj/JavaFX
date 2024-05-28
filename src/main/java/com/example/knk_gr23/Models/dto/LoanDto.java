package com.example.knk_gr23.Models.dto;

import java.util.ArrayList;
import java.util.List;
import com.example.knk_gr23.Models.dto.PaymentDto;
import com.example.knk_gr23.Services.LoanCalculatorService;

public class LoanDto {

    public int duration;  //years
    public double ir;     //interest rate
    public double amount; //Capital amount

    public double totalInterest;
    public double totalPaid;

    public List<PaymentDto> payments;

    public double monthlyPayment;


    public LoanDto(){
        payments = new ArrayList<>();
        totalInterest = 0;
        totalPaid = 0;
    }

    public LoanDto(int duration, double ir, double amount) {
        this.duration = duration;
        this.ir = ir;
        this.amount = amount;
        payments = new ArrayList<>();
        totalInterest = 0;
        totalPaid = 0;

    }
    /*
     * monthly payment
     * rate for each month
     * months for duration
     * monthly payment
     *  */
    public double calcPayment(double rate, int months){
        return (rate * amount) / (1 - Math.pow(1 + rate, -months));
    }

    /*
     * Function to build loan schedule
     * */

    public double getTotalInterest() {

        return totalInterest;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public List<PaymentDto> getPayments() {
        return payments;
    }

    public double getMonthlyPayment() {
        return this.monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
    public void setTotalInterest(double totalInterest) {
        this.totalInterest = totalInterest;
    }
    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getInterestRate() {
        return ir;
    }

    public void setInterestRate(double ir) {
        this.ir = ir;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Method to add a payment to the list
    public void addPayment(PaymentDto payment) {
        this.payments.add(payment);
    }

    // Method to add interest to the total interest
    public void addInterest(double interest) {
        this.totalInterest += interest;
    }

}


