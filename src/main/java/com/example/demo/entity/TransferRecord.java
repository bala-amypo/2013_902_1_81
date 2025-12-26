package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "transfer_records")
public class TransferRecord {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Asset asset;

    private String fromDepartment;
    private String toDepartment;
    private LocalDate transferDate;

    @ManyToOne
    private User approvedBy;

    public TransferRecord() {
    }

    public TransferRecord(Long id, Asset asset, String fromDepartment,
                          String toDepartment, LocalDate transferDate,
                          User approvedBy) {
        this.id = id;
        this.asset = asset;
        this.fromDepartment = fromDepartment;
        this.toDepartment = toDepartment;
        this.transferDate = transferDate;
        this.approvedBy = approvedBy;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Asset getAsset() { return asset; }
    public void setAsset(Asset asset) { this.asset = asset; }

    public String getFromDepartment() { return fromDepartment; }
    public String getToDepartment() { return toDepartment; }
    public LocalDate getTransferDate() { return transferDate; }

    public User getApprovedBy() { return approvedBy; }
    public void setApprovedBy(User approvedBy) { this.approvedBy = approvedBy; }

    public void setFromDepartment(String fromDepartment) {
        this.fromDepartment = fromDepartment;
    }

    public void setToDepartment(String toDepartment) {
        this.toDepartment = toDepartment;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }
}
