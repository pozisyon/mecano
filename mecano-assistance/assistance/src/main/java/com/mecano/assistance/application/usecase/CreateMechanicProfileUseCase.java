package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.command.CreateMechanicProfileCommand;
import com.mecano.assistance.application.mapper.MechanicProfileResultMapper;
import com.mecano.assistance.application.result.MechanicProfileResult;
import com.mecano.assistance.domain.model.MechanicProfile;
import com.mecano.assistance.domain.port.MechanicProfileRepositoryPort;
import com.mecano.assistance.interfaces.rest.exception.BusinessException;

public class CreateMechanicProfileUseCase {

    private final MechanicProfileRepositoryPort mechanicProfileRepository;

    public CreateMechanicProfileUseCase(
            MechanicProfileRepositoryPort mechanicProfileRepository
    ) {
        this.mechanicProfileRepository = mechanicProfileRepository;
    }

    public MechanicProfileResult execute(CreateMechanicProfileCommand command) {
        if (mechanicProfileRepository.existsByUserId(command.userId())) {
            throw new BusinessException("Mechanic profile already exists for this user");
        }

        MechanicProfile profile = MechanicProfile.create(
                command.userId(),
                command.fullName(),
                command.speciality(),
                command.location()
        );

        MechanicProfile saved = mechanicProfileRepository.save(profile);

        MechanicProfileResultMapper mechanicProfileResultMapper= null;
        return MechanicProfileResultMapper.toResult(saved);
    }
}