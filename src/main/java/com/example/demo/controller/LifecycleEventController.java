package com.example.demo.controller;

import com.example.demo.entity.LifecycleEvent;
import com.example.demo.service.LifecycleEventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@Tag(name = "LifecycleEventController")
public class LifecycleEventController {

    private final LifecycleEventService eventService;

    public LifecycleEventController(LifecycleEventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/{assetId}/{userId}")
    public ResponseEntity<LifecycleEvent> logEvent(
            @PathVariable Long assetId, 
            @PathVariable Long userId, 
            @RequestBody LifecycleEvent event) {
        return ResponseEntity.ok(eventService.logEvent(assetId, userId, event));
    }

    @GetMapping("/asset/{assetId}")
    public ResponseEntity<List<LifecycleEvent>> getEventsForAsset(@PathVariable Long assetId) {
        return ResponseEntity.ok(eventService.getEventsForAsset(assetId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LifecycleEvent> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEvent(id));
    }
}