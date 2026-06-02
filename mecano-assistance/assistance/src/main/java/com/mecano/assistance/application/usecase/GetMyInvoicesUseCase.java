package com.mecano.assistance.application.usecase;

import com.mecano.assistance.domain.model.Intervention;
import com.mecano.assistance.domain.model.Invoice;
import com.mecano.assistance.domain.port.InvoiceRepositoryPort;

import java.util.List;
import java.util.UUID;

public class GetMyInvoicesUseCase {

    private final GetMyInterventionsUseCase getMyInterventionsUseCase;
    private final InvoiceRepositoryPort invoiceRepository;

    public GetMyInvoicesUseCase(
            GetMyInterventionsUseCase getMyInterventionsUseCase,
            InvoiceRepositoryPort invoiceRepository
    ) {
        this.getMyInterventionsUseCase = getMyInterventionsUseCase;
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> execute(UUID driverId) {
        var interventionIds = getMyInterventionsUseCase.execute(driverId)
                .stream()
                .map(Intervention::getId)
                .toList();

        if (interventionIds.isEmpty()) {
            return List.of();
        }

        return invoiceRepository.findByInterventionIds(interventionIds);
    }
}