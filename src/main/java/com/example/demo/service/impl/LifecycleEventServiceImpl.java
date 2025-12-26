package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.*;
import com.example.demo.repository.*;

import java.util.List;

public class LifecycleEventServiceImpl {

    private final LifecycleEventRepository lifecycleEventRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public LifecycleEventServiceImpl(
            LifecycleEventRepository lifecycleEventRepository,
            AssetRepository assetRepository,
            UserRepository userRepository) {
        this.lifecycleEventRepository = lifecycleEventRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    public LifecycleEvent logEvent(Long assetId, Long userId, LifecycleEvent event) {
        Asset asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (event.getEventType() == null)
            throw new ValidationException("eventType required");

        if (event.getEventDescription() == null || event.getEventDescription().isEmpty())
            throw new ValidationException("eventDescription required");

        event.setAsset(asset);
        event.setPerformedBy(user);
        return lifecycleEventRepository.save(event);
    }

    public List<LifecycleEvent> getEventsForAsset(Long assetId) {
        return lifecycleEventRepository.findByAsset_Id(assetId);
    }

    public LifecycleEvent getEvent(Long id) {
        return lifecycleEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lifecycle event not found"));
    }
}
