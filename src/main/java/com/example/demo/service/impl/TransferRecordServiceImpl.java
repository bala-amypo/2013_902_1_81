package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;

import java.time.LocalDate;
import java.util.List;

public class TransferRecordServiceImpl {

    private final TransferRecordRepository transferRecordRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public TransferRecordServiceImpl(
            TransferRecordRepository transferRecordRepository,
            AssetRepository assetRepository,
            UserRepository userRepository) {
        this.transferRecordRepository = transferRecordRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    public TransferRecord createTransfer(Long assetId, TransferRecord record) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        User approver = userRepository.findById(record.getApprovedBy().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!"ADMIN".equals(approver.getRole()))
            throw new ValidationException("ADMIN required");

        if (record.getFromDepartment().equals(record.getToDepartment()))
            throw new ValidationException("departments must differ");

        if (record.getTransferDate().isAfter(LocalDate.now()))
            throw new ValidationException("Transfer date cannot be in the future");

        record.setAsset(asset);
        record.setApprovedBy(approver);
        return transferRecordRepository.save(record);
    }

    public List<TransferRecord> getTransfersForAsset(Long assetId) {
        return transferRecordRepository.findByAsset_Id(assetId);
    }

    public TransferRecord getTransfer(Long id) {
        return transferRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer record not found"));
    }
}
