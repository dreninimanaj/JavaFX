package com.example.knk_gr23.Models.dto;

public class CreateUserDto {

    private String username;
    private String email;
    private String role;
    private String salt;
    private String password_hash;

    public CreateUserDto(String username, String password_hash, String email, String role, String salt) {
        this.username = this.username;
        this.email = email;
        this.salt = salt;
        this.password_hash = password_hash;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getSalt() {
        return salt;
    }

    public String getPasswordHash() {
        return password_hash;
    }
}