package com.mecano.assistance.infrastructure.scheduling;

import com.mecano.assistance.domain.port.DispatchOfferRepositoryPort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DispatchOfferExpirationScheduler {

    private final DispatchOfferRepositoryPort dispatchOfferRepository;

    public DispatchOfferExpirationScheduler(
            DispatchOfferRepositoryPort dispatchOfferRepository
    ) {
        this.dispatchOfferRepository = dispatchOfferRepository;
    }

    @Scheduled(fixedRate = 30000)
    public void expireOffers() {
        var expiredOffers = dispatchOfferRepository.findExpiredSentOffers();

        expiredOffers.forEach(offer -> {
            offer.expire();
            dispatchOfferRepository.save(offer);
        });
    }
}