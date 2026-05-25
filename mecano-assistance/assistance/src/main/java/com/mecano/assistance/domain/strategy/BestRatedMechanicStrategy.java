package com.mecano.assistance.domain.strategy;

import com.mecano.assistance.domain.model.BreakdownRequest;
import com.mecano.assistance.domain.model.Mechanic;

import java.util.Comparator;
import java.util.List;

public class BestRatedMechanicStrategy implements MatchingStrategy {

    @Override
    public List<Mechanic> match(BreakdownRequest request, List<Mechanic> mechanics) {
        return mechanics.stream()
                .filter(Mechanic::isAvailable)
                .sorted(Comparator.comparingDouble(Mechanic::getRating).reversed())
                .limit(10)
                .toList();
    }
}