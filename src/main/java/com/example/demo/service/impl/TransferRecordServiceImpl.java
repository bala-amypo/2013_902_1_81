package com.example.demo.service.impl;

import com.example.demo.entity.Asset;
import com.example.demo.entity.TransferRecord;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.AssetRepository;
import com.example.demo.repository.TransferRecordRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.TransferRecordService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
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
    public TransferRecord createTransfer(Long assetId, TransferRecord record) {
        if (record.getTransferDate() != null && record.getTransferDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Transfer date cannot be in the future");
        }
        if (record.getFromDepartment() != null && record.getFromDepartment().equals(record.getToDepartment())) {
            throw new ValidationException("From department and To department must be different");
        }

        Asset asset = assetRepository.findById(assetId).orElseThrow();
        record.setAsset(asset);
        return transferRecordRepository.save(record);
    }

    @Override
    public List<TransferRecord> getTransfersForAsset(Long assetId) {
        return transferRecordRepository.findByAssetId(assetId);
    }

    @Override
    public TransferRecord getTransfer(Long id) {
        return transferRecordRepository.findById(id).orElseThrow();
    }
}