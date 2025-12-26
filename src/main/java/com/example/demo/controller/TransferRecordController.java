package com.example.demo.controller;

import com.example.demo.entity.TransferRecord;
import com.example.demo.service.TransferRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfers")
public class TransferRecordController {

    private final TransferRecordService transferRecordService;

    public TransferRecordController(TransferRecordService transferRecordService) {
        this.transferRecordService = transferRecordService;
    }

    @PostMapping
    public ResponseEntity<TransferRecord> transferAsset(
            @RequestBody TransferRecord record) {
        return ResponseEntity.ok(
                transferRecordService.transferAsset(record)
        );
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<TransferRecord>> getTransfersByAsset(
            @PathVariable Long assetId) {
        return ResponseEntity.ok(
                transferRecordService.getTransfersByAsset(assetId)
        );
    }
}
