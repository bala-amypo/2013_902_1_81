package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String assetTag;

    private String assetName;

    private String assetType;

    private String status;

    private String currentHolder;

    private LocalDateTime createdAt;

    // ✅ Required by JPA
    public Asset() {
    }

    // ✅ FULL constructor required by test cases
    public Asset(Long id,
                 String assetTag,
                 String assetName,
                 String assetType,
                 String status,
                 String currentHolder,
                 LocalDateTime createdAt) {
        this.id = id;
        this.assetTag = assetTag;
        this.assetName = assetName;
        this.assetType = assetType;
        this.status = status;
        this.currentHolder = currentHolder;
        this.createdAt = createdAt;
    }

    // ✅ Common constructor
    public Asset(String assetTag,
                 String assetName,
                 String assetType,
                 String status,
                 String currentHolder) {
        this.assetTag = assetTag;
        this.assetName = assetName;
        this.assetType = assetType;
        this.status = status;
        this.currentHolder = currentHolder;
    }

    // ✅ Auto timestamp
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

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentHolder() {
        return currentHolder;
    }

    public void setCurrentHolder(String currentHolder) {
        this.currentHolder = currentHolder;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
