//package com.example.knk_gr23.Reposirtory;
//
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
////import model.User;
////import model.dto.CreateUserDto;
////import service.DBConnector;
//
//public class UserRepository {
//    public UserRepository() {
//    }
//
//    public static boolean create(CreateUserDto userData) {
//        Connection conn = DBConnector.getConnection();
//        String query = "INSERT INTO USER (firstName, lastName, email, salt, passwordHash)\nVALUE (?, ?, ?, ?, ?)\n";
//
//        try {
//            PreparedStatement pst = conn.prepareStatement(query);
//            pst.setString(1, userData.getFirstName());
//            pst.setString(2, userData.getLastName());
//            pst.setString(3, userData.getEmail());
//            pst.setString(4, userData.getSalt());
//            pst.setString(5, userData.getPasswordHash());
//            pst.execute();
//            pst.close();
//            conn.close();
//            return true;
//        } catch (Exception var4) {
//            return false;
//        }
//    }
//
//    public static User getByEmail(String email) {
//        String query = "SELECT * FROM USER WHERE email = ? LIMIT 1";
//        Connection connection = DBConnector.getConnection();
//
//        try {
//            PreparedStatement pst = connection.prepareStatement(query);
//            pst.setString(1, email);
//            ResultSet result = pst.executeQuery();
//            return result.next() ? getFromResultSet(result) : null;
//        } catch (Exception var5) {
//            return null;
//        }
//    }
//
//    private static User getFromResultSet(ResultSet result) {
//        try {
//            int id = result.getInt("id");
//            String firstName = result.getString("firstName");
//            String lastName = result.getString("lastName");
//            String email = result.getString("email");
//            String salt = result.getString("salt");
//            String passwordHash = result.getString("passwordHash");
//            return new User(id, firstName, lastName, email, salt, passwordHash);
//        } catch (Exception var7) {
//            return null;
//        }
//    }
//}
