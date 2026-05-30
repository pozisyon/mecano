package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.mapper.MechanicProfileResultMapper;
import com.mecano.assistance.application.result.MechanicProfileResult;
import com.mecano.assistance.domain.port.MechanicProfileRepositoryPort;
import com.mecano.assistance.domain.valueobject.Location;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;

import java.util.UUID;

public class UpdateMechanicLocationUseCase {

    private final MechanicProfileRepositoryPort mechanicProfileRepository;

    public UpdateMechanicLocationUseCase(
            MechanicProfileRepositoryPort mechanicProfileRepository
    ) {
        this.mechanicProfileRepository = mechanicProfileRepository;
    }

    public MechanicProfileResult execute(
            UUID userId,
            Location location
    ) {
        var profile = mechanicProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Mechanic profile not found"));

        profile.updateLocation(location);

        return MechanicProfileResultMapper.toResult(
                mechanicProfileRepository.save(profile)
        );
    }
}