package com.example.knk_gr23.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
//    private static final String URL = "jdbc:mysql://localhost:3306/MenaxhimiKredise";
//    private static final String USER = "root";
//    private static final String PASSWORD = "dreni123";
//    private static Connection connection = null;
//
//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            System.err.println("Failed to load MySQL driver: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    public static Connection getConnection() {
//        if (connection == null) {
//            try {
//                connection = DriverManager.getConnection(URL, USER, PASSWORD);
//                System.out.println("Successfully connected to the database.");
//            } catch (SQLException e) {
//                System.err.println("Connection failed: " + e.getMessage());
//                e.printStackTrace();
//            }
//        }
//        return connection;
//    }
    private static final String URL = "jdbc:mysql://localhost:3306/MenaxhimiKredise";
    private static final String USER = "root";
    private static final String PASSWORD = "220756100077";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load MySQL driver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
