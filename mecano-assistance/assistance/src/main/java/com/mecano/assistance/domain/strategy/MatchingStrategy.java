package com.mecano.assistance.domain.strategy;

import com.mecano.assistance.domain.model.BreakdownRequest;
import com.mecano.assistance.domain.model.Mechanic;

import java.util.List;

public interface MatchingStrategy {

    List<Mechanic> match(
            BreakdownRequest request,
            List<Mechanic> mechanics
    );
}