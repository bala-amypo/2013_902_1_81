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

    public TransferRecordServiceImpl(TransferRecordRepository transferRecordRepository,
                                     AssetRepository assetRepository,
                                     UserRepository userRepository) {
        this.transferRecordRepository = transferRecordRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TransferRecord transferAsset(TransferRecord record) {

        if (record.getTransferDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Transfer date cannot be in the future");
        }

        Asset asset = assetRepository.findById(record.getAsset().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        User approver = userRepository.findById(record.getApprovedBy().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Approver not found"));

        if (!"ADMIN".equals(approver.getRole())) {
            throw new ValidationException("Only admin can approve transfer");
        }

        record.setAsset(asset);
        record.setApprovedBy(approver);

        asset.setStatus("TRANSFERRED");
        assetRepository.save(asset);

        return transferRecordRepository.save(record);
    }

    @Override
    public List<TransferRecord> getTransfersByAsset(Long assetId) {
        return transferRecordRepository.findByAsset_Id(assetId);
    }
}
