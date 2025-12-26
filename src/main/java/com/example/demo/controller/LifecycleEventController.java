package com.example.demo.controller;

import com.example.demo.entity.LifecycleEvent;
import com.example.demo.service.LifecycleEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lifecycle-events")
public class LifecycleEventController {

    private final LifecycleEventService lifecycleEventService;

    public LifecycleEventController(LifecycleEventService lifecycleEventService) {
        this.lifecycleEventService = lifecycleEventService;
    }

    @PostMapping
    public ResponseEntity<LifecycleEvent> createEvent(
            @RequestBody LifecycleEvent event) {
        return ResponseEntity.ok(lifecycleEventService.createEvent(event));
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<LifecycleEvent>> getEventsByAsset(
            @PathVariable Long assetId) {
        return ResponseEntity.ok(
                lifecycleEventService.getEventsByAsset(assetId)
        );
    }
}
