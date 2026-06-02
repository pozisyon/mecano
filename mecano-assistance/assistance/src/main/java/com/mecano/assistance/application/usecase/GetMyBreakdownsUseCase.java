package com.mecano.assistance.application.usecase;

import com.mecano.assistance.domain.port.BreakdownRepositoryPort;

import java.util.UUID;

public class GetMyBreakdownsUseCase {

    private final BreakdownRepositoryPort breakdownRepository;

    public GetMyBreakdownsUseCase(BreakdownRepositoryPort breakdownRepository) {
        this.breakdownRepository = breakdownRepository;
    }

    public Object execute(UUID driverId) {
        return breakdownRepository.findByDriverId(driverId);
    }
}