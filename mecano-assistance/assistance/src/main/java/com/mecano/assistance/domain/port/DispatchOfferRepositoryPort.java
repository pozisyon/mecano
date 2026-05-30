package com.mecano.assistance.domain.port;

import com.mecano.assistance.domain.model.DispatchOffer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DispatchOfferRepositoryPort {

    DispatchOffer save(DispatchOffer offer);

    List<DispatchOffer> saveAll(List<DispatchOffer> offers);

    Optional<DispatchOffer> findActiveOffer(UUID breakdownRequestId, UUID mechanicId);

    List<DispatchOffer> findByBreakdownRequestId(UUID breakdownRequestId);
    List<DispatchOffer> findActiveOffersByMechanicId(UUID mechanicId);
    Optional<DispatchOffer> findById(UUID offerId);
    List<DispatchOffer> findExpiredSentOffers();

    boolean hasActiveOffers(UUID breakdownRequestId);

    boolean hasAcceptedOffer(UUID breakdownRequestId);

    List<UUID> findMechanicIdsAlreadyOffered(UUID breakdownRequestId);
}