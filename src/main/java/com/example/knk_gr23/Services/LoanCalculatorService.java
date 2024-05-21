package com.example.knk_gr23.Services;

import com.example.knk_gr23.Models.dto.PaymentDto;

import java.util.List;

import com.example.knk_gr23.Models.dto.LoanDto;
import com.example.knk_gr23.Controllers.Client.LoanCalculatorController;

public class LoanCalculatorService {
    public void buildTable(){

        LoanDto loan = new LoanDto();
        double monthlyRate = loan.ir/ 12 /100;

        int months = loan.duration * 12;

        loan.monthlyPayment = this.calcPayment(monthlyRate, months);

        double irPayment, amountPaid, newBalance;

        double balance = loan.amount;

        for (int month = 1; month <= months; month++) {
            irPayment = balance * monthlyRate; // Corrected this line
            amountPaid = loan.monthlyPayment - irPayment;
            newBalance = balance - amountPaid;

            PaymentDto object = new PaymentDto(month, balance, loan.monthlyPayment, irPayment, amountPaid, newBalance);

            loan.payments.add(object);

            loan.totalInterest += irPayment;
            balance = newBalance; // Update balance for the next iteration
        }


        loan.totalPaid = loan.amount + loan.totalInterest;

    }

    public double calcPayment(double rate, int months){
        LoanDto loan = new LoanDto();
        return (rate * loan.amount) / (1 - Math.pow(1 + rate, -months));
    }

    /*
     * Fu
     * nction to build loan schedule
     * */
}
