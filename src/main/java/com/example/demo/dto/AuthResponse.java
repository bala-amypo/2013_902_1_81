package com.example.demo.dto;

public class AuthResponse {
    private String token;
    private String message;

    // Ensure this constructor matches the two arguments used in AuthController
    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}