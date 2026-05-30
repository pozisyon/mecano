package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.command.RejectDispatchOfferCommand;
import com.mecano.assistance.domain.port.DispatchOfferRepositoryPort;
import com.mecano.assistance.interfaces.rest.exception.BusinessException;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;

public class RejectDispatchOfferUseCase {

    private final DispatchOfferRepositoryPort dispatchOfferRepository;

    public RejectDispatchOfferUseCase(DispatchOfferRepositoryPort dispatchOfferRepository) {
        this.dispatchOfferRepository = dispatchOfferRepository;
    }

    public void execute(RejectDispatchOfferCommand command) {
        var offer = dispatchOfferRepository.findById(command.offerId())
                .orElseThrow(() -> new NotFoundException("Dispatch offer not found"));

        if (!offer.getMechanicId().equals(command.mechanicId())) {
            throw new BusinessException("This dispatch offer does not belong to authenticated mechanic");
        }

        offer.reject();
        dispatchOfferRepository.save(offer);
    }
}