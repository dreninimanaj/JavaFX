package com.example.knk_gr23.Reposirtory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.knk_gr23.Models.User;
import com.example.knk_gr23.Models.dto.CreateClientDto;
import com.example.knk_gr23.Models.dto.CreateUserDto;
import com.example.knk_gr23.database.DatabaseUtil;

public class UserRepository {

    public static boolean signUp(CreateClientDto userData) {
        String userQuery = """
                INSERT INTO users (username, password_hash, email, role, salt)
                VALUES (?, ?, ?, ?, ?)
                """;
        String clientQuery = """
                INSERT INTO clients (name, address, phone, email, employment_status, income, credit_history, debt_to_income_ratio, users_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement userStmt = conn.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement clientStmt = conn.prepareStatement(clientQuery)) {

            conn.setAutoCommit(false);  // Start transaction

            // Insert into users table
            userStmt.setString(1, userData.getUsername());
            userStmt.setString(2, userData.getPasswordHash());
            userStmt.setString(3, userData.getEmail());
            userStmt.setString(4, userData.getRole());
            userStmt.setString(5, userData.getSalt());
            userStmt.executeUpdate();

            // Get the generated user_id
            ResultSet rs = userStmt.getGeneratedKeys();
            if (rs.next()) {
                int userId = rs.getInt(1);

                // Insert into clients table
                clientStmt.setString(1, userData.getName());
                clientStmt.setString(2, userData.getAddress());
                clientStmt.setString(3, userData.getPhone());
                clientStmt.setString(4, userData.getEmail());
                clientStmt.setString(5, userData.getEmploymentStatus());
                clientStmt.setDouble(6, userData.getIncome());
                clientStmt.setString(7, userData.getCreditHistory());
                clientStmt.setDouble(8, userData.getDebtToIncomeRatio());
                clientStmt.setInt(9, userId);
                clientStmt.executeUpdate();

                conn.commit();  // Commit transaction
                return true;
            } else {
                conn.rollback();  // Rollback transaction if no user ID was generated
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Failed to sign up user: " + e.getMessage());
            e.printStackTrace();
            try (Connection conn = DatabaseUtil.getConnection()) {
                conn.rollback();  // Rollback transaction on error
            } catch (SQLException rollbackEx) {
                System.err.println("Failed to rollback transaction: " + rollbackEx.getMessage());
                rollbackEx.printStackTrace();
            }
            return false;
        }
    }
    public static boolean create(CreateUserDto userData) throws SQLException {
        String query = """
                INSERT INTO users (username, password_hash, email, role, salt)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, userData.getUsername());
            pst.setString(2, userData.getPasswordHash());
            pst.setString(3, userData.getEmail());
            pst.setString(4, userData.getRole());
            pst.setString(5, userData.getSalt());
            pst.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Failed to create user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static User getByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Log the prepared statement and the username being queried
            System.out.println("Preparing statement: " + query);
            System.out.println("Setting username parameter: " + username);

            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {

                // Log the execution of the query
                System.out.println("Executing query...");

                if (rs.next()) {
                    // Create and return the User object from the ResultSet
                    return getFromResultSet(rs);
                } else {
                    // Log if no user was found
                    System.out.println("No user found for username: " + username);
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch user by username: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        String query = "SELECT username FROM users";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                usernames.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch usernames: " + e.getMessage());
            e.printStackTrace();
        }
        return usernames;
    }

    private static User getFromResultSet(ResultSet result) throws SQLException {
        int user_id = result.getInt("user_id");
        String username = result.getString("username");
        String password_hash = result.getString("password_hash");
        String email = result.getString("email");
        String role = result.getString("role");
        String salt = result.getString("salt");

        return new User(user_id, username, password_hash, email, role, salt);
    }
}
