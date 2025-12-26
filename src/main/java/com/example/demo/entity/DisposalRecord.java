package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "disposal_records")
public class DisposalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reason;

    private String approvedBy;

    private LocalDateTime disposalDate;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    // ✅ Required by JPA
    public DisposalRecord() {
    }

    // ✅ FULL constructor required by test cases
    public DisposalRecord(Long id,
                          Asset asset,
                          String reason,
                          String approvedBy,
                          LocalDateTime disposalDate) {
        this.id = id;
        this.asset = asset;
        this.reason = reason;
        this.approvedBy = approvedBy;
        this.disposalDate = disposalDate;
    }

    // ✅ Common constructor
    public DisposalRecord(String reason, Asset asset) {
        this.reason = reason;
        this.asset = asset;
    }

    // ✅ Auto-set disposal date
    @PrePersist
    public void prePersist() {
        this.disposalDate = LocalDateTime.now();
    }

    // ===== GETTERS & SETTERS (ALL REQUIRED BY TESTS) =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDateTime getDisposalDate() {
        return disposalDate;
    }

    public void setDisposalDate(LocalDateTime disposalDate) {
        this.disposalDate = disposalDate;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
