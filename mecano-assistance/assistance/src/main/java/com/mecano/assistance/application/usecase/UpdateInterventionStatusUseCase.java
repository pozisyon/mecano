package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.command.UpdateInterventionStatusCommand;
import com.mecano.assistance.application.result.UpdateInterventionStatusResult;
import com.mecano.assistance.domain.port.InterventionRepositoryPort;
import com.mecano.assistance.domain.port.RealtimeNotificationPort;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;

public class UpdateInterventionStatusUseCase {

    private final InterventionRepositoryPort interventionRepository;
    private final RealtimeNotificationPort realtimeNotificationPort;
    public UpdateInterventionStatusUseCase(InterventionRepositoryPort interventionRepository, RealtimeNotificationPort real) {
        this.interventionRepository = interventionRepository;
        this.realtimeNotificationPort = real;
    }

    public UpdateInterventionStatusResult execute(UpdateInterventionStatusCommand command) {
        var intervention = interventionRepository.findById(command.interventionId())
                .orElseThrow(() -> new NotFoundException("Intervention not found"));//new IllegalArgumentException("Intervention not found"));

        intervention.changeStatus(command.newStatus());

        var saved = interventionRepository.save(intervention);
        realtimeNotificationPort.sendToMechanic(
                saved.getMechanicId().toString(),
                "INTERVENTION_STATUS_UPDATED",
                saved
        );

        return new UpdateInterventionStatusResult(
                saved.getId(),
                saved.getStatus()
        );
    }
}