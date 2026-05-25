package com.mecano.assistance.infrastructure.persistence.entity;

import com.mecano.assistance.domain.model.BreakdownStatus;
import com.mecano.assistance.domain.model.BreakdownType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

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

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getDriverId() { return driverId; }
    public void setDriverId(UUID driverId) { this.driverId = driverId; }

    public UUID getVehicleId() { return vehicleId; }
    public void setVehicleId(UUID vehicleId) { this.vehicleId = vehicleId; }

    public BreakdownType getType() { return type; }
    public void setType(BreakdownType type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public BreakdownStatus getStatus() { return status; }
    public void setStatus(BreakdownStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}