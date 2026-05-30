package com.mecano.assistance.application.usecase;

import com.mecano.assistance.domain.model.BreakdownStatus;
import com.mecano.assistance.domain.port.*;
import com.mecano.assistance.domain.service.DispatchDomainService;
import com.mecano.assistance.domain.service.MatchingDomainService;

public class RedispatchPendingBreakdownsUseCase {

    private final BreakdownRepositoryPort breakdownRepository;
    private final MechanicRepositoryPort mechanicRepository;
    private final DispatchOfferRepositoryPort dispatchOfferRepository;
    private final MatchingDomainService matchingDomainService;
    private final DispatchDomainService dispatchDomainService;
    private final NotificationPort notificationPort;
    private static final int MAX_DISPATCH_ATTEMPTS = 3;

    public RedispatchPendingBreakdownsUseCase(
            BreakdownRepositoryPort breakdownRepository,
            MechanicRepositoryPort mechanicRepository,
            DispatchOfferRepositoryPort dispatchOfferRepository,
            MatchingDomainService matchingDomainService,
            DispatchDomainService dispatchDomainService,
            NotificationPort notificationPort
    ) {
        this.breakdownRepository = breakdownRepository;
        this.mechanicRepository = mechanicRepository;
        this.dispatchOfferRepository = dispatchOfferRepository;
        this.matchingDomainService = matchingDomainService;
        this.dispatchDomainService = dispatchDomainService;
        this.notificationPort = notificationPort;
    }

    public void execute() {
        var pendingRequests = breakdownRepository.findByStatus(BreakdownStatus.PENDING);

        pendingRequests.forEach(request -> {
            boolean hasActiveOffer = dispatchOfferRepository.hasActiveOffers(request.getId());
            boolean hasAcceptedOffer = dispatchOfferRepository.hasAcceptedOffer(request.getId());

            if (hasActiveOffer || hasAcceptedOffer) {
                return;
            }

          //  var mechanics = mechanicRepository.findAvailableMechanicsNear(request.getLocation());
           // var matchedMechanics = matchingDomainService.findBestMechanics(request, mechanics);
            var alreadyOfferedMechanicIds =
                    dispatchOfferRepository.findMechanicIdsAlreadyOffered(request.getId());

            var mechanics = mechanicRepository.findAvailableMechanicsNear(request.getLocation())
                    .stream()
                    .filter(mechanic -> !alreadyOfferedMechanicIds.contains(mechanic.getId()))
                    .toList();

            var matchedMechanics = matchingDomainService.findBestMechanics(request, mechanics);

            if (matchedMechanics.isEmpty()) {
                return;
            }

            if (request.hasReachedMaxDispatchAttempts(MAX_DISPATCH_ATTEMPTS)) {
                request.expire();
                breakdownRepository.save(request);
                return;
            }

            var offers = dispatchDomainService.createOffers(request, matchedMechanics);
            dispatchOfferRepository.saveAll(offers);

            request.incrementDispatchAttempts();
            breakdownRepository.save(request);

            matchedMechanics.forEach(mechanic ->
                    notificationPort.notifyMechanic(mechanic, request)
            );
        });
    }
}