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
    public void buildTable(){

        double monthlyRate = ir / 12 /100;

        int months = duration * 12;

        monthlyPayment = calcPayment(monthlyRate, months);

        double irPayment, amountPaid, newBalance;

        double balance = amount;

        for (int month = 1; month <= months; month++) {
            irPayment = balance * monthlyRate; // Corrected this line
            amountPaid = monthlyPayment - irPayment;
            newBalance = balance - amountPaid;

            PaymentDto object = new PaymentDto(month, balance, monthlyPayment, irPayment, amountPaid, newBalance);

            payments.add(object);

            this.totalInterest += irPayment;
            balance = newBalance; // Update balance for the next iteration
        }


        totalPaid = amount + totalInterest;

   }

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


}


