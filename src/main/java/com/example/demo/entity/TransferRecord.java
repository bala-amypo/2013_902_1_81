package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfer_records")
public class TransferRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromDepartment;
    private String toDepartment;

    private LocalDateTime transferDate;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    public TransferRecord() {
    }

    public TransferRecord(String fromDepartment, String toDepartment, Asset asset) {
        this.fromDepartment = fromDepartment;
        this.toDepartment = toDepartment;
        this.asset = asset;
        this.transferDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getFromDepartment() {
        return fromDepartment;
    }

    public void setFromDepartment(String fromDepartment) {
        this.fromDepartment = fromDepartment;
    }

    public String getToDepartment() {
        return toDepartment;
    }

    public void setToDepartment(String toDepartment) {
        this.toDepartment = toDepartment;
    }

    public LocalDateTime getTransferDate() {
        return transferDate;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
