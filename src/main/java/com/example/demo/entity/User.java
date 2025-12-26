package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String department;

    private String password;

    private String role;

    private LocalDateTime createdAt;

    // ✅ REQUIRED BY JPA
    public User() {
    }

    // ✅ REQUIRED BY TEST CASES (FULL CONSTRUCTOR)
    public User(Long id,
                String fullName,
                String email,
                String department,
                String password,
                String role,
                LocalDateTime createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    // ✅ REQUIRED BY CONTROLLER
    public User(String fullName,
                String email,
                String department,
                String password,
                String role) {
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.password = password;
        this.role = role;
    }

    // ✅ AUTO SET createdAt
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    // ===== GETTERS & SETTERS (ALL REQUIRED BY TESTS) =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
