package com.mecano.assistance.application.usecase;

import com.mecano.assistance.domain.model.BreakdownRequest;
import com.mecano.assistance.domain.model.Intervention;
import com.mecano.assistance.domain.port.BreakdownRepositoryPort;
import com.mecano.assistance.domain.port.InterventionRepositoryPort;

import java.util.List;
import java.util.UUID;

public class GetMyInterventionsUseCase {

    private final BreakdownRepositoryPort breakdownRepository;
    private final InterventionRepositoryPort interventionRepository;

    public GetMyInterventionsUseCase(
            BreakdownRepositoryPort breakdownRepository,
            InterventionRepositoryPort interventionRepository
    ) {
        this.breakdownRepository = breakdownRepository;
        this.interventionRepository = interventionRepository;
    }

    public List<Intervention> execute(UUID driverId) {
        var breakdownIds = breakdownRepository.findByDriverId(driverId)
                .stream()
                .map(BreakdownRequest::getId)
                .toList();

        if (breakdownIds.isEmpty()) {
            return List.of();
        }

        return interventionRepository.findByBreakdownRequestIds(breakdownIds);
    }
}