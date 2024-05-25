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

    public static boolean create(Client client) throws SQLException {
        String query = """
                INSERT INTO clients (name, address, phone, email, employment_status, income, credit_history, debt_to_income_ratio, users_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, client.getName());
            pst.setString(2, client.getAddress());
            pst.setString(3, client.getPhone());
            pst.setString(4, client.getEmail());
            pst.setString(5, client.getEmploymentStatus());
            pst.setBigDecimal(6, client.getIncome());
            pst.setString(7, client.getCreditHistory());
            pst.setBigDecimal(8, client.getDebtToIncomeRatio());
            pst.setInt(9, client.getUsersId());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Failed to create client: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

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

    public static Client getClientByUserId(int userId) throws SQLException {
        String query = "SELECT * FROM clients WHERE users_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getClientFromResultSet(rs);
                }
            }
        }
        return null;
    }

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

        return new Client(clientId, name, address, phone, email, employmentStatus, income, creditHistory, debtToIncomeRatio, usersId);
    }
}





