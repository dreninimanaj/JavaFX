package com.example.knk_gr23.Services;

import com.example.knk_gr23.Models.Loan;
import com.example.knk_gr23.Reposirtory.PendingLoanRepository;

import java.sql.SQLException;
import java.util.List;

public class PendingLoanService {

    public static List<Loan> getAllPendingLoans() throws SQLException {
        return PendingLoanRepository.getAllPendingLoans();
    }
}
