package com.example.demo.repository;

import com.example.demo.entity.TransferRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransferRecordRepository extends JpaRepository<TransferRecord, Long> {

    // Used in tests: transfer history for an asset
    List<TransferRecord> findByAsset_Id(Long assetId);
}
