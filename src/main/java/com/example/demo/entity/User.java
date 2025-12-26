package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String department;
    private String role;
    private String password;
    private LocalDateTime createdAt;

    // No-arg constructor
    public User() {
    }

    // Parameterized constructor
    public User(Long id, String fullName, String email, String department,
                String role, String password, LocalDateTime createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.role = role;
        this.password = password;
        this.createdAt = createdAt;
    }

    // PrePersist defaults
    @PrePersist
    public void prePersist() {
        if (this.role == null) {
            this.role = "USER";
        }
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    // ✅ REQUIRED FOR UserServiceImpl
    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
