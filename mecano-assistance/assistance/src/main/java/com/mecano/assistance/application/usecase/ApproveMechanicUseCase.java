package com.mecano.assistance.application.usecase;

import com.mecano.assistance.domain.port.MechanicRepositoryPort;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;

import java.util.UUID;

public class ApproveMechanicUseCase {

    private final MechanicRepositoryPort mechanicRepository;

    public ApproveMechanicUseCase(MechanicRepositoryPort mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    public void execute(UUID mechanicId) {
        var mechanic = mechanicRepository.findById(mechanicId)
                .orElseThrow(() -> new NotFoundException("Mechanic not found"));

        mechanic.approve();

        mechanicRepository.save(mechanic);
    }
}