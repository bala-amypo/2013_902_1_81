package com.example.demo.repository;

import com.example.demo.entity.LifecycleEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LifecycleEventRepository extends JpaRepository<LifecycleEvent, Long> {

    // Used in tests: find events by asset id
    List<LifecycleEvent> findByAsset_Id(Long assetId);
}
