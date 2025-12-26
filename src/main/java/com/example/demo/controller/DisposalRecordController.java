package com.example.demo.controller;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disposals")
public class DisposalRecordController {

    private final DisposalRecordService disposalRecordService;

    public DisposalRecordController(DisposalRecordService disposalRecordService) {
        this.disposalRecordService = disposalRecordService;
    }

    @PostMapping
    public ResponseEntity<DisposalRecord> disposeAsset(
            @RequestBody DisposalRecord record) {
        return ResponseEntity.ok(
                disposalRecordService.disposeAsset(record)
        );
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<DisposalRecord> getByAsset(
            @PathVariable Long assetId) {
        return ResponseEntity.ok(
                disposalRecordService.getByAsset(assetId)
        );
    }
}
