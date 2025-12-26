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

    @PrePersist
    public void prePersist() {
        if (this.role == null) this.role = "USER";
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getRole() { return role; }
    public String getPassword() { return password; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
