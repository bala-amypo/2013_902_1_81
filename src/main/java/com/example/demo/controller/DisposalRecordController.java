package com.example.demo.controller;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposals")
public class DisposalRecordController {

    private final DisposalRecordService disposalRecordService;

    public DisposalRecordController(DisposalRecordService disposalRecordService) {
        this.disposalRecordService = disposalRecordService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisposalRecord> getDisposal(@PathVariable Long id) {
        return ResponseEntity.ok(disposalRecordService.getDisposal(id));
    }

    @PostMapping("/asset/{assetId}")
    public ResponseEntity<DisposalRecord> createDisposal(
            @PathVariable Long assetId,
            @RequestBody DisposalRecord record
    ) {
        return ResponseEntity.ok(
                disposalRecordService.createDisposal(assetId, record)
        );
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<DisposalRecord>> getByAsset(
            @PathVariable Long assetId
    ) {
        return ResponseEntity.ok(
                disposalRecordService.getByAsset(assetId)
        );
    }
}
