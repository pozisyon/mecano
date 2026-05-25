package com.mecano.assistance.application.usecase;

import com.mecano.assistance.domain.model.Mechanic;
import com.mecano.assistance.domain.port.BreakdownRepositoryPort;
import com.mecano.assistance.domain.port.MechanicRepositoryPort;
import com.mecano.assistance.domain.port.NotificationPort;
import com.mecano.assistance.domain.service.MatchingDomainService;

import java.util.List;
import java.util.UUID;

public class FindMatchingMechanicsUseCase {

    private final BreakdownRepositoryPort breakdownRepository;
    private final MechanicRepositoryPort mechanicRepository;
    private final MatchingDomainService matchingDomainService;
    private final NotificationPort notificationPort;

    public FindMatchingMechanicsUseCase(
            BreakdownRepositoryPort breakdownRepository,
            MechanicRepositoryPort mechanicRepository,
            MatchingDomainService matchingDomainService,
            NotificationPort notificationPort
    ) {
        this.breakdownRepository = breakdownRepository;
        this.mechanicRepository = mechanicRepository;
        this.matchingDomainService = matchingDomainService;
        this.notificationPort = notificationPort;
    }

    public List<Mechanic> execute(UUID breakdownRequestId) {
        var request = breakdownRepository.findById(breakdownRequestId)
                .orElseThrow(() -> new IllegalArgumentException("Breakdown request not found"));

        var mechanics = mechanicRepository.findAvailableMechanicsNear(request.getLocation());

        var matchedMechanics = matchingDomainService.findBestMechanics(request, mechanics);

        matchedMechanics.forEach(mechanic ->
                notificationPort.notifyMechanic(mechanic, request)
        );

        return matchedMechanics;
    }
}