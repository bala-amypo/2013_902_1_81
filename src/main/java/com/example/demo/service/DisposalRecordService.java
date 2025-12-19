package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.DisposalRecord;

public interface DisposalRecordService {
    DisposalRecord createDisposal(Long assetId, DisposalRecord disposal);
    DisposalRecord getDisposal(Long id);
    List<DisposalRecord> getAllDisposals();
}