package com.example.knk_gr23.Reposirtory;

import com.example.knk_gr23.Models.Filter.PaymentFilter;
import com.example.knk_gr23.Models.dto.PaymentDto;
import com.example.knk_gr23.database.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentsRepository {

    public static List<PaymentDto> findPaymentsByLoanId(int loanId) {
        List<PaymentDto> payments = new ArrayList<>();
        String query = "SELECT * FROM payments WHERE loan_id = ? ORDER BY month ASC";

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

    public static boolean removeTopPaymentForLoan(int loanId) {
        String selectTopPaymentSQL = "SELECT payment_id FROM payments WHERE loan_id = ? ORDER BY month ASC LIMIT 1";
        String deletePaymentSQL = "DELETE FROM payments WHERE payment_id = ?";

        try (Connection connection = DatabaseUtil.getConnection()) {
            // Fetch the top payment ID
            PreparedStatement selectStatement = connection.prepareStatement(selectTopPaymentSQL);
            selectStatement.setInt(1, loanId);

            int topPaymentId = -1;
            try (ResultSet rs = selectStatement.executeQuery()) {
                if (rs.next()) {
                    topPaymentId = rs.getInt("payment_id");
                }
            }

            // If top payment exists, delete it
            if (topPaymentId != -1) {
                PreparedStatement deleteStatement = connection.prepareStatement(deletePaymentSQL);
                deleteStatement.setInt(1, topPaymentId);
                int affectedRows = deleteStatement.executeUpdate();

                return affectedRows > 0;
            } else {
                System.out.println("No payments found for loan ID: " + loanId);
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Failed to remove top payment: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public static List<PaymentDto> findPaymentsByFilter(PaymentFilter filter) {
        List<PaymentDto> payments = new ArrayList<>();
        String query = filter.buildQuery();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

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
