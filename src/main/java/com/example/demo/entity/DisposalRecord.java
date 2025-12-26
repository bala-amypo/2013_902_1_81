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

    private LocalDateTime disposalDate;

    @OneToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    public DisposalRecord() {
    }

    public DisposalRecord(String reason, Asset asset) {
        this.reason = reason;
        this.asset = asset;
        this.disposalDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getDisposalDate() {
        return disposalDate;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
