package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.TransferRecord;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.TransferRecordRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransferRecordService;

import java.time.LocalDate;
import java.util.List;

public class TransferRecordServiceImpl implements TransferRecordService {

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

    @Override
    public TransferRecord createTransfer(Long assetId, TransferRecord record) {

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        User approver = record.getApprovedBy();
        if (approver == null || !"ADMIN".equals(approver.getRole())) {
            throw new ValidationException("ADMIN");
        }

        if (record.getFromDepartment().equals(record.getToDepartment())) {
            throw new ValidationException("departments must differ");
        }

        if (record.getTransferDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Transfer date cannot be in the future");
        }

        record.setAsset(asset);
        return transferRecordRepository.save(record);
    }

    @Override
    public List<TransferRecord> getTransfersForAsset(Long assetId) {
        return transferRecordRepository.findByAsset_Id(assetId);
    }

    @Override
    public TransferRecord getTransfer(Long id) {
        return transferRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer record not found"));
    }
}
