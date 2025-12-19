package com.example.demo.controller;

import com.example.demo.entity.DisposalRecord;
import com.example.demo.service.DisposalRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disposals")
@Tag(name = "DisposalRecordController")
public class DisposalRecordController {

    private final DisposalRecordService disposalService;

    public DisposalRecordController(DisposalRecordService disposalService) {
        this.disposalService = disposalService;
    }

    @PostMapping("/{assetId}")
    public ResponseEntity<DisposalRecord> createDisposal(
            @PathVariable Long assetId, 
            @RequestBody DisposalRecord record) {
        return ResponseEntity.ok(disposalService.createDisposal(assetId, record));
    }

    @GetMapping
    public ResponseEntity<List<DisposalRecord>> getAllDisposals() {
        return ResponseEntity.ok(disposalService.getAllDisposals());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisposalRecord> getDisposal(@PathVariable Long id) {
        return ResponseEntity.ok(disposalService.getDisposal(id));
    }
}