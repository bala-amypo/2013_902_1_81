package com.example.demo.service;

import com.example.demo.entity.DisposalRecord;

public interface DisposalRecordService {

    DisposalRecord disposeAsset(DisposalRecord record);

    DisposalRecord getByAsset(Long assetId);
}
