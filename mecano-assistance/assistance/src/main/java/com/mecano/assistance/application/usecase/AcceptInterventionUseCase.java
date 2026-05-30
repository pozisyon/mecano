package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.command.AcceptInterventionCommand;
import com.mecano.assistance.application.result.AcceptInterventionResult;
import com.mecano.assistance.domain.model.BreakdownStatus;
import com.mecano.assistance.domain.model.Intervention;
import com.mecano.assistance.domain.port.*;
import com.mecano.assistance.domain.service.MechanicEligibilityService;
import com.mecano.assistance.interfaces.rest.exception.BusinessException;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;

public class AcceptInterventionUseCase {

    private final BreakdownRepositoryPort breakdownRepository;
    private final MechanicRepositoryPort mechanicRepository;
    private final InterventionRepositoryPort interventionRepository;
    private final NotificationPort notificationPort;
    private final RealtimeNotificationPort realtimeNotificationPort;
    private final MechanicEligibilityService eligibilityService;
    private final DispatchOfferRepositoryPort dispatchOfferRepository;

    public AcceptInterventionUseCase(
            BreakdownRepositoryPort breakdownRepository,
            MechanicRepositoryPort mechanicRepository,
            InterventionRepositoryPort interventionRepository,
            NotificationPort notificationPort,
            MechanicEligibilityService eligibilityService,
            DispatchOfferRepositoryPort dispatchOfferRepository,
            RealtimeNotificationPort realtimeNotificationPort
    ) {
        this.breakdownRepository = breakdownRepository;
        this.mechanicRepository = mechanicRepository;
        this.interventionRepository = interventionRepository;
        this.notificationPort = notificationPort;
        this.eligibilityService = eligibilityService;
        this.dispatchOfferRepository = dispatchOfferRepository;
        this.realtimeNotificationPort = realtimeNotificationPort;
    }

    public AcceptInterventionResult execute(AcceptInterventionCommand command) {
        var breakdown = breakdownRepository.findById(command.breakdownRequestId())
                .orElseThrow(() -> new NotFoundException("Intervention not found"));//IllegalArgumentException("Breakdown request not found"));

        if (breakdown.getStatus() != BreakdownStatus.PENDING) {
            throw new BusinessException("Breakdown request already assigned");//throw new IllegalStateException("Breakdown request is no longer available");
        }

        if (interventionRepository.existsByBreakdownRequestId(command.breakdownRequestId())) {
            throw new BusinessException("Breakdown request already assigned");//throw new IllegalStateException("Breakdown request already assigned");
        }

        var mechanic = mechanicRepository.findById(command.mechanicId())
                .orElseThrow(() -> new NotFoundException("Intervention not found"));//IllegalArgumentException("Mechanic not found"));

        if (!eligibilityService.canAccept(mechanic, breakdown)) {
            throw new BusinessException("Mechanic is not eligible to accept this breakdown request");
        }
        if (!mechanic.isAvailable()) {
            throw new BusinessException("Breakdown request already assigned");//throw new IllegalStateException("Mechanic is not available");
        }

        breakdown.markAsAccepted();
        breakdownRepository.save(breakdown);

        var intervention = Intervention.accept(
                breakdown.getId(),
                mechanic.getId()
        );

        var saved = interventionRepository.save(intervention);

        notificationPort.notifyDriverInterventionAccepted(saved);
        realtimeNotificationPort.sendToDriver(
                breakdown.getDriverId().toString(),
                "INTERVENTION_ACCEPTED",
                saved
        );

        return new AcceptInterventionResult(
                saved.getId(),
                saved.getBreakdownRequestId(),
                saved.getMechanicId(),
                saved.getStatus()
        );
    }
}