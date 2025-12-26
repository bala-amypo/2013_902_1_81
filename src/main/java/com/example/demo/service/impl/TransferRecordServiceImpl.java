package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.TransferRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.TransferRecordRepository;
import com.example.demo.service.TransferRecordService;

import java.time.LocalDate;
import java.util.List;

public class TransferRecordServiceImpl implements TransferRecordService {

    private final TransferRecordRepository transferRecordRepository;
    private final AssetRepository assetRepository;

    public TransferRecordServiceImpl(
            TransferRecordRepository transferRecordRepository,
            AssetRepository assetRepository
    ) {
        this.transferRecordRepository = transferRecordRepository;
        this.assetRepository = assetRepository;
    }

    @Override
    public TransferRecord createTransfer(Long assetId, TransferRecord record) {
        if (record.getTransferDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Transfer date cannot be in the future");
        }

        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        record.setAsset(asset);
        asset.setStatus("TRANSFERRED");
        assetRepository.save(asset);

        return transferRecordRepository.save(record);
    }

    @Override
    public List<TransferRecord> getTransfersForAsset(Long assetId) {
        return transferRecordRepository.findByAsset_Id(assetId);
    }
}
