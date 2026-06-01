package com.mecano.assistance.infrastructure.persistence.adapter;

import com.mecano.assistance.domain.model.Invoice;
import com.mecano.assistance.domain.port.InvoiceRepositoryPort;
import com.mecano.assistance.infrastructure.persistence.mapper.InvoiceMapper;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataInvoiceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InvoiceRepositoryAdapter implements InvoiceRepositoryPort {

    private final SpringDataInvoiceRepository repository;

    public InvoiceRepositoryAdapter(SpringDataInvoiceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Invoice save(Invoice invoice) {
        return InvoiceMapper.toDomain(
                repository.save(InvoiceMapper.toEntity(invoice))
        );
    }

    @Override
    public Optional<Invoice> findById(UUID id) {
        return repository.findById(id).map(InvoiceMapper::toDomain);
    }

    @Override
    public Optional<Invoice> findByPaymentId(UUID paymentId) {
        return repository.findByPaymentId(paymentId).map(InvoiceMapper::toDomain);
    }

    @Override
    public List<Invoice> findByInterventionId(UUID interventionId) {
        return repository.findByInterventionId(interventionId)
                .stream()
                .map(InvoiceMapper::toDomain)
                .toList();
    }
}