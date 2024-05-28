package com.example.knk_gr23.Reposirtory;

import com.example.knk_gr23.App.SessionMenager;
import com.example.knk_gr23.Models.Loan;
import com.example.knk_gr23.Models.User;
import com.example.knk_gr23.Services.LoanService;
import com.example.knk_gr23.database.DatabaseUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.knk_gr23.Models.Client;

public class ClientRepository {

    // Method to get all clients
    public static List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients WHERE 1 = 1";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // Log the execution of the query
            System.out.println("Executing query: " + query);

            while (rs.next()) {
                clients.add(getClientFromResultSet(rs));

            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch clients: " + e.getMessage());
            e.printStackTrace();
        }
        return clients;
    }

    // Helper method to create a User object from a ResultSet
    private static User getFromResultSet(ResultSet result) {
        try {
            int user_id = result.getInt("user_id");
            String username = result.getString("username");
            String password_hash = result.getString("password_hash");
            String email = result.getString("email");
            String role = result.getString("role");
            String salt = result.getString("salt");

            return new User(user_id, username, password_hash, email, role, salt);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Client findClientByUserId(int userId) {
        String query = "SELECT * FROM clients WHERE users_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);
            System.out.println("Executing query: " + query + " with user_id: " + userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getClientFromResultSet(rs);
                } else {
                    System.out.println("No client found for user_id: " + userId);
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch client by user ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null; // or throw an exception if preferred
    }


    // Helper method to create a Client object from a ResultSet
    private static Client getClientFromResultSet(ResultSet rs) throws SQLException {
        int clientId = rs.getInt("client_id");
        String name = rs.getString("name");
        String address = rs.getString("address");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String employmentStatus = rs.getString("employment_status");
        BigDecimal income = rs.getBigDecimal("income");
        String creditHistory = rs.getString("credit_history");
        BigDecimal debtToIncomeRatio = rs.getBigDecimal("debt_to_income_ratio");
        int usersId = rs.getInt("users_id");
        System.out.println("Client found: " + clientId);
        return new Client(clientId, name, address, phone, email, employmentStatus, income, creditHistory, debtToIncomeRatio, usersId);
    }
}





