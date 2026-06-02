package com.mecano.assistance.infrastructure.persistence.repository;

import com.mecano.assistance.infrastructure.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataPaymentRepository extends JpaRepository<PaymentEntity, UUID> {

    Optional<PaymentEntity> findByInterventionId(UUID interventionId);
    List<PaymentEntity> findByInterventionIdIn(
            List<UUID> interventionIds
    );
}