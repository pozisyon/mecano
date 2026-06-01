package com.mecano.assistance.domain.model;

import com.mecano.assistance.domain.valueobject.Location;

import java.time.LocalDateTime;
import java.util.UUID;

public class BreakdownRequest {

    private final UUID id;
    private final UUID driverId;
    private final UUID vehicleId;
    private final BreakdownType type;
    private final String description;
    private final Location location;
    private BreakdownStatus status;
    private final LocalDateTime createdAt;
    private int dispatchAttempts;
    private BreakdownRequest(
            UUID id,
            UUID driverId,
            UUID vehicleId,
            BreakdownType type,
            String description,
            Location location,
            BreakdownStatus status,
            LocalDateTime createdAt,
            int dispatchAttempts
    ) {
        this.id = id;
        this.driverId = driverId;
        this.vehicleId = vehicleId;
        this.type = type;
        this.description = description;
        this.location = location;
        this.status = status;
        this.createdAt = createdAt;
        this.dispatchAttempts = dispatchAttempts;
    }

    public static BreakdownRequest create(
            UUID driverId,
            UUID vehicleId,
            BreakdownType type,
            String description,
            Location location
    ) {
        if (driverId == null) throw new IllegalArgumentException("Driver is required");
        if (vehicleId == null) throw new IllegalArgumentException("Vehicle is required");
        if (type == null) throw new IllegalArgumentException("Breakdown type is required");
        if (location == null) throw new IllegalArgumentException("Location is required");

        return new BreakdownRequest(
                UUID.randomUUID(),
                driverId,
                vehicleId,
                type,
                description,
                location,
                BreakdownStatus.PENDING,
                LocalDateTime.now(),
                0
        );
    }

    public void markAsAccepted() {
        if (this.status != BreakdownStatus.PENDING) {
            throw new IllegalStateException("Only pending requests can be accepted");
        }
        this.status = BreakdownStatus.ACCEPTED;
    }

    public void cancel() {
        if (this.status == BreakdownStatus.ACCEPTED) {
            throw new IllegalStateException("Accepted request cannot be cancelled directly");
        }
        this.status = BreakdownStatus.CANCELLED;
    }
    public static BreakdownRequest restore(
            UUID id,
            UUID driverId,
            UUID vehicleId,
            BreakdownType type,
            String description,
            Location location,
            BreakdownStatus status,
            LocalDateTime createdAt,
            int dispatchAttempts
    ) {
        BreakdownRequest request = new BreakdownRequest(
                id,
                driverId,
                vehicleId,
                type,
                description,
                location,
                status,
                createdAt,
                dispatchAttempts

        );

        request.status = status;
        return request;
    }

    public UUID getId() { return id; }
    public UUID getDriverId() { return driverId; }
    public UUID getVehicleId() { return vehicleId; }
    public BreakdownType getType() { return type; }
    public String getDescription() { return description; }
    public Location getLocation() { return location; }
    public BreakdownStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public int getDispatchAttempts(){return dispatchAttempts;}

    public void incrementDispatchAttempts() {
        this.dispatchAttempts++;
    }

    public boolean hasReachedMaxDispatchAttempts(int maxAttempts) {
        return this.dispatchAttempts >= maxAttempts;
    }

    public void expire() {
        if (this.status != BreakdownStatus.PENDING) {
            throw new IllegalStateException("Only pending breakdown request can expire");
        }
        this.status = BreakdownStatus.EXPIRED;
    }
}