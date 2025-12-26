package com.example.demo.service.impl;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.repository.DisposalRecordRepository;
import com.example.demo.service.DisposalRecordService;
import org.springframework.stereotype.Service;

@Service
public class DisposalRecordServiceImpl implements DisposalRecordService {

    private final DisposalRecordRepository disposalRecordRepository;

    public DisposalRecordServiceImpl(DisposalRecordRepository disposalRecordRepository) {
        this.disposalRecordRepository = disposalRecordRepository;
    }

    @Override
    public DisposalRecord disposeAsset(DisposalRecord record) {
        return disposalRecordRepository.save(record);
    }

    @Override
    public DisposalRecord getByAsset(Long assetId) {
        return disposalRecordRepository.findByAsset_Id(assetId).orElse(null);
    }
}
