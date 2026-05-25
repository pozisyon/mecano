package com.mecano.assistance.infrastructure.persistence.adapter;

import com.mecano.assistance.domain.model.Payment;
import com.mecano.assistance.domain.port.PaymentRepositoryPort;
import com.mecano.assistance.infrastructure.persistence.mapper.PaymentMapper;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataPaymentRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

    private final SpringDataPaymentRepository repository;

    public PaymentRepositoryAdapter(SpringDataPaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Payment save(Payment payment) {
        var entity = PaymentMapper.toEntity(payment);
        var saved = repository.save(entity);
        return PaymentMapper.toDomain(saved);
    }

    @Override
    public Optional<Payment> findById(UUID id) {
        return repository.findById(id).map(PaymentMapper::toDomain);
    }

    @Override
    public Optional<Payment> findByInterventionId(UUID interventionId) {
        return repository.findByInterventionId(interventionId)
                .map(PaymentMapper::toDomain);
    }
}