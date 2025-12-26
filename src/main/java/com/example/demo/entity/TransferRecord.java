package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class TransferRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromDepartment;
    private String toDepartment;
    private LocalDate transferDate;

    @ManyToOne
    private Asset asset;

    @ManyToOne
    private User approvedBy;

    public TransferRecord() {}

    public TransferRecord(Long id, String fromDepartment, String toDepartment,
                          LocalDate transferDate, Asset asset, User approvedBy) {
        this.id = id;
        this.fromDepartment = fromDepartment;
        this.toDepartment = toDepartment;
        this.transferDate = transferDate;
        this.asset = asset;
        this.approvedBy = approvedBy;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFromDepartment() { return fromDepartment; }
    public void setFromDepartment(String fromDepartment) { this.fromDepartment = fromDepartment; }

    public String getToDepartment() { return toDepartment; }
    public void setToDepartment(String toDepartment) { this.toDepartment = toDepartment; }

    public LocalDate getTransferDate() { return transferDate; }
    public void setTransferDate(LocalDate transferDate) { this.transferDate = transferDate; }

    public Asset getAsset() { return asset; }
    public void setAsset(Asset asset) { this.asset = asset; }

    public User getApprovedBy() { return approvedBy; }
    public void setApprovedBy(User approvedBy) { this.approvedBy = approvedBy; }
}
