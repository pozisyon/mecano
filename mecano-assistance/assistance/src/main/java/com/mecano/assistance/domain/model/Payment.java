package com.mecano.assistance.domain.model;

import com.mecano.assistance.domain.valueobject.Money;

import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {

    private final UUID id;
    private final UUID interventionId;
    private final Money amount;
    private PaymentStatus status;
    private final String method;
    private final LocalDateTime createdAt;

    private Payment(
            UUID id,
            UUID interventionId,
            Money amount,
            PaymentStatus status,
            String method,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.interventionId = interventionId;
        this.amount = amount;
        this.status = status;
        this.method = method;
        this.createdAt = createdAt;
    }

    public static Payment create(UUID interventionId, Money amount, String method) {
        if (interventionId == null) throw new IllegalArgumentException("Intervention is required");
        if (amount == null) throw new IllegalArgumentException("Amount is required");

        return new Payment(
                UUID.randomUUID(),
                interventionId,
                amount,
                PaymentStatus.PENDING,
                method,
                LocalDateTime.now()
        );
    }

    public static Payment restore(
            UUID id,
            UUID interventionId,
            Money amount,
            PaymentStatus status,
            String method,
            LocalDateTime createdAt
    ) {
        return new Payment(id, interventionId, amount, status, method, createdAt);
    }

    public void confirm() {
        status = PaymentStatus.PAID;
    }

    public void fail() {
        status = PaymentStatus.FAILED;
    }

    public UUID getId() { return id; }
    public UUID getInterventionId() { return interventionId; }
    public Money getAmount() { return amount; }
    public PaymentStatus getStatus() { return status; }
    public String getMethod() { return method; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}