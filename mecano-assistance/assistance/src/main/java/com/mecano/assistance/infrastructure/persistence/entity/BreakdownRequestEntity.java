package com.mecano.assistance.infrastructure.persistence.entity;

import com.mecano.assistance.domain.model.BreakdownStatus;
import com.mecano.assistance.domain.model.BreakdownType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "breakdown_requests")
public class BreakdownRequestEntity {

    @Id
    private UUID id;

    private UUID driverId;
    private UUID vehicleId;

    @Enumerated(EnumType.STRING)
    private BreakdownType type;

    private String description;

    private double latitude;
    private double longitude;

    @Enumerated(EnumType.STRING)
    private BreakdownStatus status;

    private LocalDateTime createdAt;

   // private int dispatchAttempts;
   @Column(name = "dispatch_attempts", nullable = false)
   private Integer dispatchAttempts = 0;

}