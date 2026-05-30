package com.mecano.assistance.application.usecase;

import com.mecano.assistance.domain.model.DispatchOffer;
import com.mecano.assistance.domain.port.DispatchOfferRepositoryPort;

import java.util.List;
import java.util.UUID;

public class GetMyDispatchOffersUseCase {

    private final DispatchOfferRepositoryPort dispatchOfferRepository;

    public GetMyDispatchOffersUseCase(
            DispatchOfferRepositoryPort dispatchOfferRepository
    ) {
        this.dispatchOfferRepository = dispatchOfferRepository;
    }

    public List<DispatchOffer> execute(UUID mechanicId) {
        return dispatchOfferRepository.findActiveOffersByMechanicId(mechanicId);
    }
}