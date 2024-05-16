package com.example.knk_gr23.Models;

import java.sql.ResultSet;

public class Model {
    static class User {
        private int id;
        private String username;

        private User(int id, String username) {
            this.id = id;
            this.username = username;
        }

        public static User getInstanceFromResultSet(ResultSet resultSet) {
            try {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                return new User(id, username);
            } catch (Exception var3) {
                return null;
            }
        }

        public void printoDetajet() {
            System.out.println("Id: " + this.id);
            System.out.println("Username: " + this.username);
        }
    }
}
