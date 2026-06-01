package com.mecano.assistance.application.usecase;

import com.mecano.assistance.domain.model.Invoice;
import com.mecano.assistance.domain.port.InvoiceRepositoryPort;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;

import java.util.UUID;

public class GetInvoiceUseCase {

    private final InvoiceRepositoryPort invoiceRepository;

    public GetInvoiceUseCase(InvoiceRepositoryPort invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice execute(UUID invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new NotFoundException("Invoice not found"));
    }
}