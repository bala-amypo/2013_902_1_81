package com.example.demo.service;

import com.example.demo.entity.TransferRecord;

import java.util.List;

public interface TransferRecordService {

    TransferRecord transferAsset(TransferRecord record);

    List<TransferRecord> getTransfersByAsset(Long assetId);
}
