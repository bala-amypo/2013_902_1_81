package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String department;
    private String role;
    private String password;
    private LocalDateTime createdAt;

    public User() {}

    // FULL constructor required by tests
    public User(Long id, String name, String email, String department,
                String role, String password, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.role = role;
        this.password = password;
        this.createdAt = createdAt;
    }

    // ===== setters REQUIRED by services =====
    public void setId(Long id) { this.id = id; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
    public void setDepartment(String department) { this.department = department; }
    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }

    // ===== getters =====
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getRole() { return role; }
    public String getPassword() { return password; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @PrePersist
    public void prePersist() {
        if (this.role == null) this.role = "USER";
        if (this.createdAt == null) this.createdAt = LocalDateTime.now();
    }
}
