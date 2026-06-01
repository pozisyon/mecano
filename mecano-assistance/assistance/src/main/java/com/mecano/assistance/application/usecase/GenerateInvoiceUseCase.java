package com.mecano.assistance.application.usecase;

import com.mecano.assistance.domain.model.Invoice;
import com.mecano.assistance.domain.port.InvoiceRepositoryPort;

import java.math.BigDecimal;
import java.util.UUID;

public class GenerateInvoiceUseCase {

    private final InvoiceRepositoryPort invoiceRepository;

    public GenerateInvoiceUseCase(InvoiceRepositoryPort invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice execute(UUID interventionId, UUID paymentId, BigDecimal amount, String currency) {
        invoiceRepository.findByPaymentId(paymentId)
                .ifPresent(existing -> {
                    throw new IllegalStateException("Invoice already exists for this payment");
                });

        Invoice invoice = Invoice.issue(interventionId, paymentId, amount, currency);
        return invoiceRepository.save(invoice);
    }
}