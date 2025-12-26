package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "disposal_records")
public class DisposalRecord {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Asset asset;

    private String disposalMethod;
    private LocalDate disposalDate;

    @ManyToOne
    private User approvedBy;

    private String notes;
    private LocalDateTime createdAt;

    public DisposalRecord() {}

    public DisposalRecord(Long id, Asset asset, String disposalMethod,
                          LocalDate disposalDate, User approvedBy,
                          String notes, LocalDateTime createdAt) {
        this.id = id;
        this.asset = asset;
        this.disposalMethod = disposalMethod;
        this.disposalDate = disposalDate;
        this.approvedBy = approvedBy;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Asset getAsset() { return asset; }
    public void setAsset(Asset asset) { this.asset = asset; }
    public String getDisposalMethod() { return disposalMethod; }
    public void setDisposalMethod(String disposalMethod) { this.disposalMethod = disposalMethod; }
    public LocalDate getDisposalDate() { return disposalDate; }
    public void setDisposalDate(LocalDate disposalDate) { this.disposalDate = disposalDate; }
    public User getApprovedBy() { return approvedBy; }
    public void setApprovedBy(User approvedBy) { this.approvedBy = approvedBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
