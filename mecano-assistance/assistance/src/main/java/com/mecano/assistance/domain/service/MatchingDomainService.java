package com.mecano.assistance.domain.service;

import com.mecano.assistance.domain.model.BreakdownRequest;
import com.mecano.assistance.domain.model.Mechanic;
import com.mecano.assistance.domain.strategy.MatchingStrategy;

import java.util.List;

public class MatchingDomainService {

    private final MatchingStrategy matchingStrategy;

    public MatchingDomainService(MatchingStrategy matchingStrategy) {
        this.matchingStrategy = matchingStrategy;
    }

    public List<Mechanic> findBestMechanics(
            BreakdownRequest request,
            List<Mechanic> availableMechanics
    ) {
        if (request == null) {
            throw new IllegalArgumentException("Breakdown request is required");
        }

        if (availableMechanics == null || availableMechanics.isEmpty()) {
            return List.of();
        }

        return matchingStrategy.match(request, availableMechanics);
    }
}