package com.example.demo.dto;

public class RegisterRequest {

    private String fullName;
    private String email;
    private String department;
    private String password;

    public RegisterRequest() {}

    public RegisterRequest(String fullName, String email,
                           String department, String password) {
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.password = password;
    }

    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getPassword() { return password; }
}
