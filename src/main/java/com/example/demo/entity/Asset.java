package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assetTag;
    private String type;
    private String manufacturer;
    private LocalDate purchaseDate;
    private String status;

    @ManyToOne
    private User currentHolder;

    private LocalDateTime createdAt;

    public Asset() {}

    public Asset(Long id, String assetTag, String type, String manufacturer,
                 LocalDate purchaseDate, String status,
                 User currentHolder, LocalDateTime createdAt) {
        this.id = id;
        this.assetTag = assetTag;
        this.type = type;
        this.manufacturer = manufacturer;
        this.purchaseDate = purchaseDate;
        this.status = status;
        this.currentHolder = currentHolder;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        if (this.status == null) this.status = "AVAILABLE";
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAssetTag() { return assetTag; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public User getCurrentHolder() { return currentHolder; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
