package com.mecano.assistance.domain.service;

import com.mecano.assistance.domain.model.BreakdownRequest;
import com.mecano.assistance.domain.model.DispatchOffer;
import com.mecano.assistance.domain.model.Mechanic;

import java.util.List;

public class DispatchDomainService {

    private static final int OFFER_TTL_SECONDS = 900;

    public List<DispatchOffer> createOffers(
            BreakdownRequest request,
            List<Mechanic> mechanics
    ) {
        return mechanics.stream()
                .map(mechanic -> DispatchOffer.create(
                        request.getId(),
                        mechanic.getId(),
                        OFFER_TTL_SECONDS
                ))
                .toList();
    }
}