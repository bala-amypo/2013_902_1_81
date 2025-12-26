package com.example.demo.dto;

public class RegisterRequest {

    private String name;
    private String email;
    private String department;
    private String password;

    public RegisterRequest() {}

    public RegisterRequest(String name, String email, String department, String password) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getPassword() {
        return password;
    }
}
