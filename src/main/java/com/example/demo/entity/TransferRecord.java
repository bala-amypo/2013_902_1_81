package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_records")
public class TransferRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromHolder;

    private String toHolder;

    private String approvedBy;

    private LocalDateTime transferDate;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    // ✅ Required by JPA
    public TransferRecord() {
    }

    // ✅ FULL constructor required by test cases
    public TransferRecord(Long id,
                          String fromHolder,
                          String toHolder,
                          String approvedBy,
                          LocalDateTime transferDate,
                          Asset asset) {
        this.id = id;
        this.fromHolder = fromHolder;
        this.toHolder = toHolder;
        this.approvedBy = approvedBy;
        this.transferDate = transferDate;
        this.asset = asset;
    }

    // ✅ Common constructor
    public TransferRecord(String fromHolder,
                          String toHolder,
                          String approvedBy,
                          Asset asset) {
        this.fromHolder = fromHolder;
        this.toHolder = toHolder;
        this.approvedBy = approvedBy;
        this.asset = asset;
    }

    // ✅ Auto set transferDate
    @PrePersist
    public void prePersist() {
        this.transferDate = LocalDateTime.now();
    }

    // ===== GETTERS & SETTERS (ALL REQUIRED BY TESTS) =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromHolder() {
        return fromHolder;
    }

    public void setFromHolder(String fromHolder) {
        this.fromHolder = fromHolder;
    }

    public String getToHolder() {
        return toHolder;
    }

    public void setToHolder(String toHolder) {
        this.toHolder = toHolder;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDateTime transferDate) {
        this.transferDate = transferDate;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
