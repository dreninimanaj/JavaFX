package com.example.knk_gr23.Services;

import com.example.knk_gr23.Models.dto.PaymentDto;
import com.example.knk_gr23.Reposirtory.LoanCalculatorRepository;


import com.example.knk_gr23.Models.dto.LoanDto;

import java.sql.SQLException;
import java.util.List;

public class LoanCalculatorService {
    public static void buildTable(LoanDto loan, int clientId) throws SQLException {
        double monthlyRate = loan.getInterestRate() / 12 / 100;
        int months = loan.getDuration() * 12;
        loan.setMonthlyPayment(loan.calcPayment(monthlyRate, months));

        double balance = loan.getAmount();
        for (int month = 1; month <= months; month++) {
            double interestPayment = balance * monthlyRate;
            double amountPaid = loan.getMonthlyPayment() - interestPayment;
            double newBalance = balance - amountPaid;

            PaymentDto payment = new PaymentDto(month, balance, loan.getMonthlyPayment(), interestPayment, amountPaid, newBalance);
            loan.addPayment(payment);

            loan.addInterest(interestPayment);
            balance = newBalance;
        }

        loan.setTotalPaid(loan.getAmount() + loan.getTotalInterest());
        LoanCalculatorRepository.saveLoan(loan, clientId);
    }

    public double calcPayment(double rate, int months){
        LoanDto loan = new LoanDto();
        return (rate * loan.amount) / (1 - Math.pow(1 + rate, -months));
    }

    public static List<PaymentDto> getPaymentsForLoan(int loanId) {
        return LoanCalculatorRepository.findPaymentsByLoanId(loanId);
    }


    public static LoanDto getLoanById(int loanId) {
        return LoanCalculatorRepository.findLoanById(loanId);
    }

}

