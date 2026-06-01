package com.mecano.assistance.domain.port;

import com.mecano.assistance.domain.model.Invoice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InvoiceRepositoryPort {

    Invoice save(Invoice invoice);

    Optional<Invoice> findById(UUID id);

    Optional<Invoice> findByPaymentId(UUID paymentId);

    List<Invoice> findByInterventionId(UUID interventionId);
}