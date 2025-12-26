package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;

import java.time.LocalDate;
import java.util.List;

public class DisposalRecordServiceImpl {

    private final DisposalRecordRepository disposalRecordRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public DisposalRecordServiceImpl(
            DisposalRecordRepository disposalRecordRepository,
            AssetRepository assetRepository,
            UserRepository userRepository) {
        this.disposalRecordRepository = disposalRecordRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    public DisposalRecord createDisposal(Long assetId, DisposalRecord disposal) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        User approver = userRepository.findById(disposal.getApprovedBy().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!"ADMIN".equals(approver.getRole()))
            throw new ValidationException("ADMIN required");

        if (disposal.getDisposalDate().isAfter(LocalDate.now()))
            throw new ValidationException("future");

        disposal.setAsset(asset);
        disposal.setApprovedBy(approver);
        DisposalRecord saved = disposalRecordRepository.save(disposal);

        asset.setStatus("DISPOSED");
        assetRepository.save(asset);

        return saved;
    }

    public DisposalRecord getDisposal(Long id) {
        return disposalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disposal record not found"));
    }

    public List<DisposalRecord> getAllDisposals() {
        return disposalRecordRepository.findAll();
    }
}
