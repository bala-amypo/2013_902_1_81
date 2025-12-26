package com.example.demo.service;

import com.example.demo.entity.DisposalRecord;

import java.util.List;

public interface DisposalRecordService {

    DisposalRecord getDisposal(Long id);

    DisposalRecord createDisposal(Long assetId, DisposalRecord record);

    List<DisposalRecord> getByAsset(Long assetId);
}
