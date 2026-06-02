
package com.mecano.assistance.application.usecase;

import com.mecano.assistance.domain.model.Intervention;
import com.mecano.assistance.domain.model.Payment;
import com.mecano.assistance.domain.port.PaymentRepositoryPort;

import java.util.List;
import java.util.UUID;

public class GetMyPaymentsUseCase {

    private final GetMyInterventionsUseCase getMyInterventionsUseCase;
    private final PaymentRepositoryPort paymentRepository;

    public GetMyPaymentsUseCase(
            GetMyInterventionsUseCase getMyInterventionsUseCase,
            PaymentRepositoryPort paymentRepository
    ) {
        this.getMyInterventionsUseCase = getMyInterventionsUseCase;
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> execute(UUID driverId) {
        var interventionIds = getMyInterventionsUseCase.execute(driverId)
                .stream()
                .map(Intervention::getId)
                .toList();

        if (interventionIds.isEmpty()) {
            return List.of();
        }

        return paymentRepository.findByInterventionIds(interventionIds);
    }
}