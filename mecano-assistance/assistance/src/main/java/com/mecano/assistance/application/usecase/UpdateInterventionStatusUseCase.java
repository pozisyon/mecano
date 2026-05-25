package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.command.UpdateInterventionStatusCommand;
import com.mecano.assistance.application.result.UpdateInterventionStatusResult;
import com.mecano.assistance.domain.port.InterventionRepositoryPort;

public class UpdateInterventionStatusUseCase {

    private final InterventionRepositoryPort interventionRepository;

    public UpdateInterventionStatusUseCase(InterventionRepositoryPort interventionRepository) {
        this.interventionRepository = interventionRepository;
    }

    public UpdateInterventionStatusResult execute(UpdateInterventionStatusCommand command) {
        var intervention = interventionRepository.findById(command.interventionId())
                .orElseThrow(() -> new IllegalArgumentException("Intervention not found"));

        intervention.changeStatus(command.newStatus());

        var saved = interventionRepository.save(intervention);

        return new UpdateInterventionStatusResult(
                saved.getId(),
                saved.getStatus()
        );
    }
}