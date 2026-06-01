package com.mecano.assistance.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Invoice {

    private final UUID id;
    private final UUID interventionId;
    private final UUID paymentId;
    private final String invoiceNumber;
    private final BigDecimal amount;
    private final String currency;
    private final LocalDateTime issuedAt;
    private final InvoiceStatus status;

    private Invoice(UUID id, UUID interventionId, UUID paymentId, String invoiceNumber,
                    BigDecimal amount, String currency, LocalDateTime issuedAt, InvoiceStatus status) {
        if (id == null) throw new IllegalArgumentException("Invoice id is required");
        if (interventionId == null) throw new IllegalArgumentException("Intervention id is required");
        if (paymentId == null) throw new IllegalArgumentException("Payment id is required");
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("Amount must be positive");
        if (currency == null || currency.isBlank()) throw new IllegalArgumentException("Currency is required");

        this.id = id;
        this.interventionId = interventionId;
        this.paymentId = paymentId;
        this.invoiceNumber = invoiceNumber;
        this.amount = amount;
        this.currency = currency;
        this.issuedAt = issuedAt;
        this.status = status;
    }

    public static Invoice issue(UUID interventionId, UUID paymentId, BigDecimal amount, String currency) {
        return new Invoice(
                UUID.randomUUID(),
                interventionId,
                paymentId,
                "INV-" + System.currentTimeMillis(),
                amount,
                currency,
                LocalDateTime.now(),
                InvoiceStatus.ISSUED
        );
    }

    public static Invoice restore(UUID id, UUID interventionId, UUID paymentId, String invoiceNumber,
                                  BigDecimal amount, String currency, LocalDateTime issuedAt, InvoiceStatus status) {
        return new Invoice(id, interventionId, paymentId, invoiceNumber, amount, currency, issuedAt, status);
    }

    public UUID getId() { return id; }
    public UUID getInterventionId() { return interventionId; }
    public UUID getPaymentId() { return paymentId; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public BigDecimal getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public LocalDateTime getIssuedAt() { return issuedAt; }
    public InvoiceStatus getStatus() { return status; }
}