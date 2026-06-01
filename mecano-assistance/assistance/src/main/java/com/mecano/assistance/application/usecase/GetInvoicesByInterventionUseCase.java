package com.mecano.assistance.application.usecase;

import com.mecano.assistance.domain.model.Invoice;
import com.mecano.assistance.domain.port.InvoiceRepositoryPort;

import java.util.List;
import java.util.UUID;

public class GetInvoicesByInterventionUseCase {

    private final InvoiceRepositoryPort invoiceRepository;

    public GetInvoicesByInterventionUseCase(InvoiceRepositoryPort invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> execute(UUID interventionId) {
        return invoiceRepository.findByInterventionId(interventionId);
    }
}