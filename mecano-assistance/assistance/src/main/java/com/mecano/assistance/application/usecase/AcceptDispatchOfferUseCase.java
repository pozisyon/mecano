package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.command.AcceptDispatchOfferCommand;
import com.mecano.assistance.application.result.AcceptInterventionResult;
import com.mecano.assistance.domain.model.BreakdownStatus;
import com.mecano.assistance.domain.model.Intervention;
import com.mecano.assistance.domain.port.*;
import com.mecano.assistance.domain.service.MechanicEligibilityService;
import com.mecano.assistance.interfaces.rest.exception.BusinessException;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;

public class AcceptDispatchOfferUseCase {

    private final DispatchOfferRepositoryPort dispatchOfferRepository;
    private final BreakdownRepositoryPort breakdownRepository;
    private final MechanicRepositoryPort mechanicRepository;
    private final InterventionRepositoryPort interventionRepository;
    private final NotificationPort notificationPort;
    private final MechanicEligibilityService eligibilityService;

    public AcceptDispatchOfferUseCase(
            DispatchOfferRepositoryPort dispatchOfferRepository,
            BreakdownRepositoryPort breakdownRepository,
            MechanicRepositoryPort mechanicRepository,
            InterventionRepositoryPort interventionRepository,
            NotificationPort notificationPort,
            MechanicEligibilityService eligibilityService
    ) {
        this.dispatchOfferRepository = dispatchOfferRepository;
        this.breakdownRepository = breakdownRepository;
        this.mechanicRepository = mechanicRepository;
        this.interventionRepository = interventionRepository;
        this.notificationPort = notificationPort;
        this.eligibilityService = eligibilityService;
    }

    public AcceptInterventionResult execute(AcceptDispatchOfferCommand command) {
        var offer = dispatchOfferRepository.findById(command.offerId())
                .orElseThrow(() -> new NotFoundException("Dispatch offer not found"));

        if (!offer.getMechanicId().equals(command.mechanicId())) {
            throw new BusinessException("This dispatch offer does not belong to authenticated mechanic");
        }

        if (!offer.isActive()) {
            throw new BusinessException("Dispatch offer is no longer active");
        }

        var breakdown = breakdownRepository.findById(offer.getBreakdownRequestId())
                .orElseThrow(() -> new NotFoundException("Breakdown request not found"));

        if (breakdown.getStatus() != BreakdownStatus.PENDING) {
            throw new BusinessException("Breakdown request is no longer available");
        }

        if (interventionRepository.existsByBreakdownRequestId(breakdown.getId())) {
            throw new BusinessException("Breakdown request already assigned");
        }

        var mechanic = mechanicRepository.findById(command.mechanicId())
                .orElseThrow(() -> new NotFoundException("Mechanic not found"));

        if (!eligibilityService.canAccept(mechanic, breakdown)) {
            throw new BusinessException("Mechanic is not eligible to accept this breakdown request");
        }

        offer.accept();
        dispatchOfferRepository.save(offer);

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