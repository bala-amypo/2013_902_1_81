package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String fullName;
    private String email;
    private String department;
    private String role;
    private String password;
    private LocalDateTime createdAt;

    public User() {}

    public User(Long id, String fullName, String email,
                String department, String role,
                String password, LocalDateTime createdAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.role = role;
        this.password = password;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        if (role == null) role = "USER";
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getRole() { return role; }
    public String getPassword() { return password; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
