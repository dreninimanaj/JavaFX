package com.example.knk_gr23.Reposirtory;

import com.example.knk_gr23.Models.dto.LoanDto;
import com.example.knk_gr23.Models.dto.PaymentDto;
import com.example.knk_gr23.database.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoanCalculatorRepository {

    public static void saveLoan(LoanDto loan, int clientId) throws SQLException {
        String insertLoanSQL = "INSERT INTO loans (client_id, loan_amount, interest_rate, loan_start_date, loan_end_date, loan_status, monthly_payment, total_interest, total_paid) VALUES (?, ?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL ? YEAR), 'Pending', ?, ?, ?)";
        String insertPaymentSQL = "INSERT INTO payments (loan_id, payment_date, scheduled_payment, principal_amount, interest_amount, balance, month, old_balance) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection()) {
            // Save loan details
            try (PreparedStatement loanStatement = connection.prepareStatement(insertLoanSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                loanStatement.setInt(1, clientId);
                loanStatement.setDouble(2, loan.getAmount());
                loanStatement.setDouble(3, loan.getInterestRate());
                loanStatement.setInt(4, loan.getDuration());
                loanStatement.setDouble(5, loan.getMonthlyPayment());
                loanStatement.setDouble(6, loan.getTotalInterest());
                loanStatement.setDouble(7, loan.getTotalPaid());

                int affectedRows = loanStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating loan failed, no rows affected.");
                }

                try (ResultSet generatedKeys = loanStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int loanId = generatedKeys.getInt(1);

                        // Save payment details
                        try (PreparedStatement paymentStatement = connection.prepareStatement(insertPaymentSQL)) {
                            for (PaymentDto payment : loan.getPayments()) {
                                paymentStatement.setInt(1, loanId);
                                paymentStatement.setDate(2, new java.sql.Date(System.currentTimeMillis())); // Set payment date, adjust if you have a specific date
                                paymentStatement.setDouble(3, payment.getPayment());
                                paymentStatement.setDouble(4, payment.getCapital());
                                paymentStatement.setDouble(5, payment.getInterest());
                                paymentStatement.setDouble(6, payment.getRemaining()); // Assuming balance = remaining balance
                                paymentStatement.setInt(7, payment.getMonth());
                                paymentStatement.setDouble(8, payment.getOldBalance());
                                paymentStatement.addBatch();
                            }

                            paymentStatement.executeBatch();
                        }
                    } else {
                        throw new SQLException("Creating loan failed, no ID obtained.");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to save loan: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public static LoanDto findLoanById(int loanId) {
        String query = "SELECT * FROM loans WHERE loan_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, loanId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getLoanFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch loan by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static LoanDto getLoanFromResultSet(ResultSet rs) throws SQLException {
        int duration = rs.getInt("duration");
        double interestRate = rs.getDouble("interest_rate");
        double amount = rs.getDouble("loan_amount");

        LoanDto loan = new LoanDto(duration, interestRate, amount);
        loan.setMonthlyPayment(rs.getDouble("monthly_payment"));
        loan.setTotalInterest(rs.getDouble("total_interest"));
        loan.setTotalPaid(rs.getDouble("total_paid"));

        // Retrieve and set payments
        String query = "SELECT * FROM payments WHERE loan_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, rs.getInt("loan_id"));
            try (ResultSet rsPayments = pstmt.executeQuery()) {
                while (rsPayments.next()) {
                    int month = rsPayments.getInt("month");
                    double oldBalance = rsPayments.getDouble("old_balance");
                    double payment = rsPayments.getDouble("scheduled_payment");
                    double interest = rsPayments.getDouble("interest_amount");
                    double capital = rsPayments.getDouble("principal_amount");
                    double remaining = rsPayments.getDouble("balance");

                    PaymentDto paymentDto = new PaymentDto(month, oldBalance, payment, interest, capital, remaining);
                    loan.addPayment(paymentDto);
                }
            }
        }
        return loan;
    }
    public static List<PaymentDto> findPaymentsByLoanId(int loanId) {
        List<PaymentDto> payments = new ArrayList<>();
        String query = "SELECT * FROM payments WHERE loan_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, loanId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int month = rs.getInt("month");
                    double oldBalance = rs.getDouble("old_balance");
                    double payment = rs.getDouble("scheduled_payment");
                    double interest = rs.getDouble("interest_amount");
                    double capital = rs.getDouble("principal_amount");
                    double remaining = rs.getDouble("balance");

                    PaymentDto paymentDto = new PaymentDto(month, oldBalance, payment, interest, capital, remaining);
                    payments.add(paymentDto);
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch payments: " + e.getMessage());
            e.printStackTrace();
        }

        return payments;
    }
}
