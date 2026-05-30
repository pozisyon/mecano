package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.mapper.MechanicProfileResultMapper;
import com.mecano.assistance.application.result.MechanicProfileResult;
import com.mecano.assistance.domain.port.MechanicProfileRepositoryPort;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;

import java.util.UUID;

public class GetMyMechanicProfileUseCase {

    private final MechanicProfileRepositoryPort mechanicProfileRepository;

    public GetMyMechanicProfileUseCase(
            MechanicProfileRepositoryPort mechanicProfileRepository
    ) {
        this.mechanicProfileRepository = mechanicProfileRepository;
    }

    public MechanicProfileResult execute(UUID userId) {
        return mechanicProfileRepository.findByUserId(userId)
                .map(MechanicProfileResultMapper::toResult)
                .orElseThrow(() -> new NotFoundException("Mechanic profile not found"));
    }
}