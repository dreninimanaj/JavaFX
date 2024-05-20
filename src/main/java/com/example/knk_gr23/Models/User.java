package com.example.knk_gr23.Models;


public class User {
    private int user_id;
    private String username;
    private String password_hash;
    private String email;
    private String role;
    private String salt;

    public User() {

    }
    public User(int user_id, String username, String password_hash, String email, String role, String salt) { //, String salt, String passwordHash) {
        this.user_id = user_id;
        this.username = username;
        this.password_hash = password_hash;
        this.email = email;
        this.role = role;
        this.salt = salt;
    }

    public int getId() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }
    public String getPasswordHash() {
        return password_hash;
    }


    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getSalt() {
        return salt;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.password_hash = passwordHash;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}