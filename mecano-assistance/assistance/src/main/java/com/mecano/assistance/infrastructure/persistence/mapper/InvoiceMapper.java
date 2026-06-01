package com.mecano.assistance.infrastructure.persistence.mapper;

import com.mecano.assistance.domain.model.Invoice;
import com.mecano.assistance.infrastructure.persistence.entity.InvoiceEntity;

public class InvoiceMapper {

    public static InvoiceEntity toEntity(Invoice invoice) {
        InvoiceEntity entity = new InvoiceEntity();

        entity.setId(invoice.getId());
        entity.setInterventionId(invoice.getInterventionId());
        entity.setPaymentId(invoice.getPaymentId());
        entity.setInvoiceNumber(invoice.getInvoiceNumber());
        entity.setAmount(invoice.getAmount());
        entity.setCurrency(invoice.getCurrency());
        entity.setIssuedAt(invoice.getIssuedAt());
        entity.setStatus(invoice.getStatus());

        return entity;
    }

    public static Invoice toDomain(InvoiceEntity entity) {
        return Invoice.restore(
                entity.getId(),
                entity.getInterventionId(),
                entity.getPaymentId(),
                entity.getInvoiceNumber(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getIssuedAt(),
                entity.getStatus()
        );
    }
}