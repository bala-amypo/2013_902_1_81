package com.example.demo.repository;

import com.example.demo.entity.LifecycleEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LifecycleEventRepository extends JpaRepository<LifecycleEvent, Long> {
    // Required for the GET /api/events/asset/{assetId} endpoint
    List<LifecycleEvent> findByAssetId(Long assetId);
}