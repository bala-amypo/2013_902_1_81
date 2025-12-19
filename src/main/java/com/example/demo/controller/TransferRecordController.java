package com.example.demo.controller;

import com.example.demo.entity.TransferRecord;
import com.example.demo.service.TransferRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
@Tag(name = "TransferRecordController")
public class TransferRecordController {

    private final TransferRecordService transferService;

    public TransferRecordController(TransferRecordService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/{assetId}")
    public ResponseEntity<TransferRecord> createTransfer(
            @PathVariable Long assetId, 
            @RequestBody TransferRecord record) {
        return ResponseEntity.ok(transferService.createTransfer(assetId, record));
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<TransferRecord>> getTransferHistory(@PathVariable Long assetId) {
        return ResponseEntity.ok(transferService.getTransfersForAsset(assetId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferRecord> getTransfer(@PathVariable Long id) {
        return ResponseEntity.ok(transferService.getTransfer(id));
    }
}