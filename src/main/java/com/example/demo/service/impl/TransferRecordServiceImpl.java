package com.example.demo.service.impl;

import com.example.demo.entity.TransferRecord;
import com.example.demo.repository.TransferRecordRepository;
import com.example.demo.service.TransferRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferRecordServiceImpl implements TransferRecordService {

    private final TransferRecordRepository transferRecordRepository;

    public TransferRecordServiceImpl(TransferRecordRepository transferRecordRepository) {
        this.transferRecordRepository = transferRecordRepository;
    }

    @Override
    public TransferRecord transferAsset(TransferRecord record) {
        return transferRecordRepository.save(record);
    }

    @Override
    public List<TransferRecord> getTransfersByAsset(Long assetId) {
        return transferRecordRepository.findByAsset_Id(assetId);
    }
}
