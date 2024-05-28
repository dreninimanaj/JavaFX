package com.example.knk_gr23.Services;

import java.sql.SQLException;
import java.util.List;

import com.example.knk_gr23.Models.Client;
import com.example.knk_gr23.Models.Loan;
import com.example.knk_gr23.Models.User;
import com.example.knk_gr23.Models.dto.LoanDto;
import com.example.knk_gr23.Models.dto.PaymentDto;
import com.example.knk_gr23.Reposirtory.LoanCalculatorRepository;
import com.example.knk_gr23.Reposirtory.LoanRepository;
import com.example.knk_gr23.Reposirtory.PaymentsRepository;

public class LoanService {
    // Method to get all loans by client_id
    public static List<Loan> getAllLoansByUser(Client client) throws SQLException {
        return LoanRepository.getAllLoansByUser(client);
    }


    public static Loan getLoanById(int loanId) {
        return LoanRepository.findLoanById(loanId);
    }

    public static boolean removeTop(int loanId) {
            return PaymentsRepository.removeTopPaymentForLoan(loanId);
    }

    public static boolean acceptLoan(int loanId) {
        try {
            return LoanRepository.updateLoanStatus(loanId, "approved");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
