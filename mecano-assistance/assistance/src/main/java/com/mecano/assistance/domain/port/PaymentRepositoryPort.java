package com.mecano.assistance.domain.port;

import com.mecano.assistance.domain.model.Payment;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepositoryPort {

    Payment save(Payment payment);

    Optional<Payment> findById(UUID id);

    Optional<Payment> findByInterventionId(UUID interventionId);
}