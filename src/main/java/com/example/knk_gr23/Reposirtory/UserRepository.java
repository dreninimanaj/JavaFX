package com.example.knk_gr23.Reposirtory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.knk_gr23.Models.User;
import com.example.knk_gr23.Models.dto.CreateUserDto;
import com.example.knk_gr23.database.DatabaseUtil;

public class UserRepository {

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
