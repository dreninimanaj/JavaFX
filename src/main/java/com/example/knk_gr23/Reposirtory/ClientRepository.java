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

public class ClientRepository {

    // Method to get all clients
    public static List<User> getAllClients() {
        List<User> clients = new ArrayList<>();
        String query = "SELECT * FROM users WHERE role = 'customer'";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // Log the execution of the query
            System.out.println("Executing query: " + query);

            while (rs.next()) {
                // Create and add the User object from the ResultSet to the list
                clients.add(getFromResultSet(rs));
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
}

