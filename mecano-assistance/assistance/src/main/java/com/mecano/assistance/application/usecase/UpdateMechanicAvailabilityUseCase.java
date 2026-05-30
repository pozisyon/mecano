package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.mapper.MechanicProfileResultMapper;
import com.mecano.assistance.application.result.MechanicProfileResult;
import com.mecano.assistance.domain.port.MechanicProfileRepositoryPort;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;

import java.util.UUID;

public class UpdateMechanicAvailabilityUseCase {

    private final MechanicProfileRepositoryPort mechanicProfileRepository;

    public UpdateMechanicAvailabilityUseCase(
            MechanicProfileRepositoryPort mechanicProfileRepository
    ) {
        this.mechanicProfileRepository = mechanicProfileRepository;
    }

    public MechanicProfileResult execute(UUID userId, boolean available) {
        var profile = mechanicProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Mechanic profile not found"));

        profile.updateAvailability(available);

        return MechanicProfileResultMapper.toResult(
                mechanicProfileRepository.save(profile)
        );
    }
}