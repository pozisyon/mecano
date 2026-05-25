package com.mecano.assistance.infrastructure.persistence.mapper;

import com.mecano.assistance.domain.model.Payment;
import com.mecano.assistance.domain.valueobject.Money;
import com.mecano.assistance.infrastructure.persistence.entity.PaymentEntity;

public class PaymentMapper {

    public static PaymentEntity toEntity(Payment payment) {
        PaymentEntity entity = new PaymentEntity();

        entity.setId(payment.getId());
        entity.setInterventionId(payment.getInterventionId());
        entity.setAmount(payment.getAmount().amount());
        entity.setCurrency(payment.getAmount().currency());
        entity.setStatus(payment.getStatus());
        entity.setMethod(payment.getMethod());
        entity.setCreatedAt(payment.getCreatedAt());

        return entity;
    }

    public static Payment toDomain(PaymentEntity entity) {
        return Payment.restore(
                entity.getId(),
                entity.getInterventionId(),
                new Money(entity.getAmount(), entity.getCurrency()),
                entity.getStatus(),
                entity.getMethod(),
                entity.getCreatedAt()
        );
    }
}