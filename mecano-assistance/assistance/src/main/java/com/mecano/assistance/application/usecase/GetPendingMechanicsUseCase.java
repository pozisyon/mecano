package com.mecano.assistance.application.usecase;

import com.mecano.assistance.domain.model.Mechanic;
import com.mecano.assistance.domain.port.MechanicRepositoryPort;

import java.util.List;

public class GetPendingMechanicsUseCase {

    private final MechanicRepositoryPort mechanicRepository;

    public GetPendingMechanicsUseCase(MechanicRepositoryPort mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    public List<Mechanic> execute() {
        return mechanicRepository.findPendingApproval();
    }
}