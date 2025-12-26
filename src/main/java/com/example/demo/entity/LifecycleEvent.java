package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class LifecycleEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Asset asset;

    private String eventType;
    private String eventDescription;
    private LocalDateTime eventDate;

    @ManyToOne
    private User performedBy;

    public LifecycleEvent() {}

    public LifecycleEvent(Long id, Asset asset, String eventType,
                          String eventDescription, LocalDateTime eventDate,
                          User performedBy) {
        this.id = id;
        this.asset = asset;
        this.eventType = eventType;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.performedBy = performedBy;
    }

    @PrePersist
    public void prePersist() {
        this.eventDate = LocalDateTime.now();
    }

    // getters & setters
    public void setId(Long id) { this.id = id; }
    public Asset getAsset() { return asset; }
    public User getPerformedBy() { return performedBy; }

    public void setEventType(String eventType) { this.eventType = eventType; }
    public void setEventDescription(String eventDescription) { this.eventDescription = eventDescription; }
}
