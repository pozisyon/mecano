package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.mapper.MechanicProfileResultMapper;
import com.mecano.assistance.application.result.MechanicProfileResult;
import com.mecano.assistance.domain.port.MechanicProfileRepositoryPort;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;

import java.util.UUID;

public class ApproveMechanicProfileUseCase {

    private final MechanicProfileRepositoryPort mechanicProfileRepository;

    public ApproveMechanicProfileUseCase(
            MechanicProfileRepositoryPort mechanicProfileRepository
    ) {
        this.mechanicProfileRepository = mechanicProfileRepository;
    }

    public MechanicProfileResult execute(UUID profileId) {
        var profile = mechanicProfileRepository.findById(profileId)
                .orElseThrow(() -> new NotFoundException("Mechanic profile not found"));

        profile.approve();

        return MechanicProfileResultMapper.toResult(
                mechanicProfileRepository.save(profile)
        );
    }
}