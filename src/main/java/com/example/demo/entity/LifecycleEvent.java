package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lifecycle_events")
public class LifecycleEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventType;

    private String eventDescription;

    private String performedBy;

    private LocalDateTime eventDate;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    // ✅ Required by JPA
    public LifecycleEvent() {
    }

    // ✅ FULL constructor required by test cases
    public LifecycleEvent(Long id,
                          String eventType,
                          String eventDescription,
                          String performedBy,
                          LocalDateTime eventDate,
                          Asset asset) {
        this.id = id;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.performedBy = performedBy;
        this.eventDate = eventDate;
        this.asset = asset;
    }

    // ✅ Common constructor
    public LifecycleEvent(String eventType,
                          String eventDescription,
                          String performedBy,
                          Asset asset) {
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.performedBy = performedBy;
        this.asset = asset;
    }

    // ✅ Auto set eventDate
    @PrePersist
    public void prePersist() {
        this.eventDate = LocalDateTime.now();
    }

    // ===== GETTERS & SETTERS (ALL REQUIRED BY TESTS) =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }
}
