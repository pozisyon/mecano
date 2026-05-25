package com.mecano.assistance.infrastructure.persistence.entity;

import com.mecano.assistance.domain.model.InterventionStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "interventions")
public class InterventionEntity {

    @Id
    private UUID id;

    private UUID breakdownRequestId;
    private UUID mechanicId;

    @Enumerated(EnumType.STRING)
    private InterventionStatus status;

    private LocalDateTime acceptedAt;
    private LocalDateTime completedAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getBreakdownRequestId() { return breakdownRequestId; }
    public void setBreakdownRequestId(UUID breakdownRequestId) { this.breakdownRequestId = breakdownRequestId; }

    public UUID getMechanicId() { return mechanicId; }
    public void setMechanicId(UUID mechanicId) { this.mechanicId = mechanicId; }

    public InterventionStatus getStatus() { return status; }
    public void setStatus(InterventionStatus status) { this.status = status; }

    public LocalDateTime getAcceptedAt() { return acceptedAt; }
    public void setAcceptedAt(LocalDateTime acceptedAt) { this.acceptedAt = acceptedAt; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}