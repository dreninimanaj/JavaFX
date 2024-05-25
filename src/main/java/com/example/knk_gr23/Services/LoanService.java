package com.example.knk_gr23.Services;

import java.sql.SQLException;
import java.util.List;

import com.example.knk_gr23.Models.Loan;
import com.example.knk_gr23.Models.User;
import com.example.knk_gr23.Reposirtory.LoanRepository;

public class LoanService {
    // Method to get all loans by client_id
    public static List<Loan> getAllLoansByUser(User user) throws SQLException {
        return LoanRepository.getAllLoansByUser(user);
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
