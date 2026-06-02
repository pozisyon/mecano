package com.mecano.assistance.application.usecase;

import com.mecano.assistance.domain.model.Intervention;
import com.mecano.assistance.domain.port.InterventionRepositoryPort;

import java.util.List;
import java.util.UUID;

public class GetMechanicInterventionHistoryUseCase {

    private final InterventionRepositoryPort interventionRepository;

    public GetMechanicInterventionHistoryUseCase(
            InterventionRepositoryPort interventionRepository
    ) {
        this.interventionRepository = interventionRepository;
    }

    public List<Intervention> execute(UUID mechanicId) {
        return interventionRepository.findByMechanicId(mechanicId);
    }
}