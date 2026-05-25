package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.command.AcceptInterventionCommand;
import com.mecano.assistance.application.result.AcceptInterventionResult;
import com.mecano.assistance.domain.model.BreakdownStatus;
import com.mecano.assistance.domain.model.Intervention;
import com.mecano.assistance.domain.port.BreakdownRepositoryPort;
import com.mecano.assistance.domain.port.InterventionRepositoryPort;
import com.mecano.assistance.domain.port.MechanicRepositoryPort;
import com.mecano.assistance.domain.port.NotificationPort;

public class AcceptInterventionUseCase {

    private final BreakdownRepositoryPort breakdownRepository;
    private final MechanicRepositoryPort mechanicRepository;
    private final InterventionRepositoryPort interventionRepository;
    private final NotificationPort notificationPort;

    public AcceptInterventionUseCase(
            BreakdownRepositoryPort breakdownRepository,
            MechanicRepositoryPort mechanicRepository,
            InterventionRepositoryPort interventionRepository,
            NotificationPort notificationPort
    ) {
        this.breakdownRepository = breakdownRepository;
        this.mechanicRepository = mechanicRepository;
        this.interventionRepository = interventionRepository;
        this.notificationPort = notificationPort;
    }

    public AcceptInterventionResult execute(AcceptInterventionCommand command) {
        var breakdown = breakdownRepository.findById(command.breakdownRequestId())
                .orElseThrow(() -> new IllegalArgumentException("Breakdown request not found"));

        if (breakdown.getStatus() != BreakdownStatus.PENDING) {
            throw new IllegalStateException("Breakdown request is no longer available");
        }

        if (interventionRepository.existsByBreakdownRequestId(command.breakdownRequestId())) {
            throw new IllegalStateException("Breakdown request already assigned");
        }

        var mechanic = mechanicRepository.findById(command.mechanicId())
                .orElseThrow(() -> new IllegalArgumentException("Mechanic not found"));

        if (!mechanic.isAvailable()) {
            throw new IllegalStateException("Mechanic is not available");
        }

        breakdown.markAsAccepted();
        breakdownRepository.save(breakdown);

        var intervention = Intervention.accept(
                breakdown.getId(),
                mechanic.getId()
        );

        var saved = interventionRepository.save(intervention);
        notificationPort.notifyDriverInterventionAccepted(saved);

        return new AcceptInterventionResult(
                saved.getId(),
                saved.getBreakdownRequestId(),
                saved.getMechanicId(),
                saved.getStatus()
        );
    }
}