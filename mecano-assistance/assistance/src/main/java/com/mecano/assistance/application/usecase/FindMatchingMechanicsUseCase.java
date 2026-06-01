package com.mecano.assistance.application.usecase;

import com.mecano.assistance.domain.model.Mechanic;
import com.mecano.assistance.domain.port.BreakdownRepositoryPort;
import com.mecano.assistance.domain.port.DispatchOfferRepositoryPort;
import com.mecano.assistance.domain.port.MechanicRepositoryPort;
import com.mecano.assistance.domain.port.NotificationPort;
import com.mecano.assistance.domain.service.DispatchDomainService;
import com.mecano.assistance.domain.service.MatchingDomainService;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

public class FindMatchingMechanicsUseCase {

    private final BreakdownRepositoryPort breakdownRepository;
    private final MechanicRepositoryPort mechanicRepository;
    private final MatchingDomainService matchingDomainService;
    private final NotificationPort notificationPort;
    private final DispatchDomainService dispatchDomainService;
    private final DispatchOfferRepositoryPort dispatchOfferRepository;

    public FindMatchingMechanicsUseCase(
            BreakdownRepositoryPort breakdownRepository,
            MechanicRepositoryPort mechanicRepository,
            MatchingDomainService matchingDomainService,
            NotificationPort notificationPort,
            DispatchDomainService dispatchDomainService,
            DispatchOfferRepositoryPort dispatchOfferRepository
    ) {
        this.breakdownRepository = breakdownRepository;
        this.mechanicRepository = mechanicRepository;
        this.matchingDomainService = matchingDomainService;
        this.notificationPort = notificationPort;
        this.dispatchDomainService = dispatchDomainService;
        this.dispatchOfferRepository = dispatchOfferRepository;
    }

    public List<Mechanic> execute(UUID breakdownRequestId) {
       var request = breakdownRepository.findById(breakdownRequestId)
                .orElseThrow(() -> new NotFoundException("Intervention not found"));//new IllegalArgumentException("Breakdown request not found"));

        var mechanics = mechanicRepository.findAvailableMechanicsNear(request.getLocation());
/*
        var matchedMechanics = matchingDomainService.findBestMechanics(request, mechanics);

        matchedMechanics.forEach(mechanic ->
                notificationPort.notifyMechanic(mechanic, request)
        );

        return matchedMechanics;*/
        var matchedMechanics = matchingDomainService.findBestMechanics(request, mechanics);

        var offers = dispatchDomainService.createOffers(request, matchedMechanics);
        dispatchOfferRepository.saveAll(offers);

        matchedMechanics.forEach(mechanic ->
                notificationPort.notifyMechanic(mechanic, request)
        );


        System.out.println("MATCHED MECHANICS = " + matchedMechanics.size());



        System.out.println("OFFERS CREATED = " + offers.size());

        offers.forEach(offer -> {
            System.out.println("OFFER ID = " + offer.getId());
            System.out.println("OFFER MECHANIC = " + offer.getMechanicId());
            System.out.println("OFFER STATUS = " + offer.getStatus());
            System.out.println("OFFER EXPIRES = " + offer.getExpiresAt());
        });

        dispatchOfferRepository.saveAll(offers);

        System.out.println("OFFERS SAVED");

        return matchedMechanics;
    }
}