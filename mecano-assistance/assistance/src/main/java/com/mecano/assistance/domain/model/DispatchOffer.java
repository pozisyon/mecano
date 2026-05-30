package com.mecano.assistance.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class DispatchOffer {

    private final UUID id;
    private final UUID breakdownRequestId;
    private final UUID mechanicId;
    private DispatchOfferStatus status;
    private final LocalDateTime sentAt;
    private final LocalDateTime expiresAt;

    public DispatchOffer(
            UUID id,
            UUID breakdownRequestId,
            UUID mechanicId,
            DispatchOfferStatus status,
            LocalDateTime sentAt,
            LocalDateTime expiresAt
    ) {
        this.id = id;
        this.breakdownRequestId = breakdownRequestId;
        this.mechanicId = mechanicId;
        this.status = status;
        this.sentAt = sentAt;
        this.expiresAt = expiresAt;
    }

    public static DispatchOffer create(UUID breakdownRequestId, UUID mechanicId, int ttlSeconds) {
        LocalDateTime now = LocalDateTime.now();

        return new DispatchOffer(
                UUID.randomUUID(),
                breakdownRequestId,
                mechanicId,
                DispatchOfferStatus.SENT,
                now,
                now.plusSeconds(ttlSeconds)
        );
    }

    public static DispatchOffer restore(
            UUID id,
            UUID breakdownRequestId,
            UUID mechanicId,
            DispatchOfferStatus status,
            LocalDateTime sentAt,
            LocalDateTime expiresAt
    ) {
        return new DispatchOffer(id, breakdownRequestId, mechanicId, status, sentAt, expiresAt);
    }

    public boolean isActive() {
        return status == DispatchOfferStatus.SENT
                && LocalDateTime.now().isBefore(expiresAt);
    }

    public void accept() {
        if (!isActive()) {
            throw new IllegalStateException("Dispatch offer is not active");
        }
        status = DispatchOfferStatus.ACCEPTED;
    }

    public void reject() {
        if (status != DispatchOfferStatus.SENT) {
            throw new IllegalStateException("Only sent offer can be rejected");
        }
        status = DispatchOfferStatus.REJECTED;
    }

    public void expire() {
        if (status == DispatchOfferStatus.SENT) {
            status = DispatchOfferStatus.EXPIRED;
        }
    }

    public UUID getId() { return id; }
    public UUID getBreakdownRequestId() { return breakdownRequestId; }
    public UUID getMechanicId() { return mechanicId; }
    public DispatchOfferStatus getStatus() { return status; }
    public LocalDateTime getSentAt() { return sentAt; }
    public LocalDateTime getExpiresAt() { return expiresAt; }
}