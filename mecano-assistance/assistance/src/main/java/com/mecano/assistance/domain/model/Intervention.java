package com.mecano.assistance.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Intervention {

    private final UUID id;
    private final UUID breakdownRequestId;
    private final UUID mechanicId;
    private InterventionStatus status;
    private final LocalDateTime acceptedAt;
    private LocalDateTime completedAt;

    private Intervention(
            UUID id,
            UUID breakdownRequestId,
            UUID mechanicId,
            InterventionStatus status,
            LocalDateTime acceptedAt,
            LocalDateTime completedAt
    ) {
        this.id = id;
        this.breakdownRequestId = breakdownRequestId;
        this.mechanicId = mechanicId;
        this.status = status;
        this.acceptedAt = acceptedAt;
        this.completedAt = completedAt;
    }

    public static Intervention accept(UUID breakdownRequestId, UUID mechanicId) {
        if (breakdownRequestId == null) throw new IllegalArgumentException("Breakdown request is required");
        if (mechanicId == null) throw new IllegalArgumentException("Mechanic is required");

        return new Intervention(
                UUID.randomUUID(),
                breakdownRequestId,
                mechanicId,
                InterventionStatus.ACCEPTED,
                LocalDateTime.now(),
                null
        );
    }

    public static Intervention restore(
            UUID id,
            UUID breakdownRequestId,
            UUID mechanicId,
            InterventionStatus status,
            LocalDateTime acceptedAt,
            LocalDateTime completedAt
    ) {
        return new Intervention(id, breakdownRequestId, mechanicId, status, acceptedAt, completedAt);
    }

    public void startTrip() {
        if (status != InterventionStatus.ACCEPTED) {
            throw new IllegalStateException("Only accepted intervention can move to ON_THE_WAY");
        }
        status = InterventionStatus.ON_THE_WAY;
    }

    public void arrive() {
        if (status != InterventionStatus.ON_THE_WAY) {
            throw new IllegalStateException("Only ON_THE_WAY intervention can move to ARRIVED");
        }
        status = InterventionStatus.ARRIVED;
    }

    public void startWork() {
        if (status != InterventionStatus.ARRIVED) {
            throw new IllegalStateException("Only ARRIVED intervention can move to IN_PROGRESS");
        }
        status = InterventionStatus.IN_PROGRESS;
    }

    public void complete() {
        if (status != InterventionStatus.IN_PROGRESS) {
            throw new IllegalStateException("Only IN_PROGRESS intervention can be completed");
        }
        status = InterventionStatus.COMPLETED;
        completedAt = LocalDateTime.now();
    }

    public void changeStatus(InterventionStatus newStatus) {
        if (newStatus == null) {
            throw new IllegalArgumentException("New status is required");
        }

        switch (newStatus) {
            case ON_THE_WAY -> startTrip();
            case ARRIVED -> arrive();
            case IN_PROGRESS -> startWork();
            case COMPLETED -> complete();
            case CANCELLED -> cancel();
            default -> throw new IllegalStateException("Unsupported status transition: " + newStatus);
        }
    }

    public void cancel() {
        if (status == InterventionStatus.COMPLETED ||
                status == InterventionStatus.PAID ||
                status == InterventionStatus.CLOSED) {
            throw new IllegalStateException("Completed, paid or closed intervention cannot be cancelled");
        }

        status = InterventionStatus.CANCELLED;
    }

    public void markAsPaid() {
        if (status != InterventionStatus.COMPLETED) {
            throw new IllegalStateException("Only completed intervention can be paid");
        }
        status = InterventionStatus.PAID;
    }

    public void close() {
        if (status != InterventionStatus.PAID) {
            throw new IllegalStateException("Only paid intervention can be closed");
        }
        status = InterventionStatus.CLOSED;
    }

    public UUID getId() { return id; }
    public UUID getBreakdownRequestId() { return breakdownRequestId; }
    public UUID getMechanicId() { return mechanicId; }
    public InterventionStatus getStatus() { return status; }
    public LocalDateTime getAcceptedAt() { return acceptedAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }
}