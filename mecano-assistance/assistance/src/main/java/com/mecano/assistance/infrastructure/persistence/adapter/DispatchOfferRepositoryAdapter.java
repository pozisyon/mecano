package com.mecano.assistance.infrastructure.persistence.adapter;

import com.mecano.assistance.domain.model.DispatchOffer;
import com.mecano.assistance.domain.model.DispatchOfferStatus;
import com.mecano.assistance.domain.port.DispatchOfferRepositoryPort;
import com.mecano.assistance.infrastructure.persistence.entity.DispatchOfferJpaEntity;
import com.mecano.assistance.infrastructure.persistence.mapper.DispatchOfferMapper;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataDispatchOfferRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DispatchOfferRepositoryAdapter implements DispatchOfferRepositoryPort {

    private final SpringDataDispatchOfferRepository repository;

    public DispatchOfferRepositoryAdapter(SpringDataDispatchOfferRepository repository) {
        this.repository = repository;
    }

    @Override
    public DispatchOffer save(DispatchOffer offer) {
        return toDomain(repository.save(toEntity(offer)));
    }

    @Override
    public List<DispatchOffer> saveAll(List<DispatchOffer> offers) {
        return repository.saveAll(
                offers.stream().map(this::toEntity).toList()
        ).stream().map(this::toDomain).toList();
    }
    @Override
    public Optional<DispatchOffer> findActiveOffer(UUID breakdownRequestId, UUID mechanicId) {
        return repository
                .findByBreakdownRequestIdAndMechanicIdAndStatus(
                        breakdownRequestId,
                        mechanicId,
                        DispatchOfferStatus.SENT
                )
                .map(this::toDomain);
    }

    @Override
    public List<DispatchOffer> findByBreakdownRequestId(UUID breakdownRequestId) {
        return repository.findByBreakdownRequestId(breakdownRequestId)
                .stream()
                .map(this::toDomain)
                .toList();
    }

    private DispatchOfferJpaEntity toEntity(DispatchOffer offer) {
        return new DispatchOfferJpaEntity(
                offer.getId(),
                offer.getBreakdownRequestId(),//breakdownRequestId(),
                offer.getMechanicId(),//mechanicId(),
                offer.getStatus(),///status().name(),

                offer.getSentAt(),//sentAt(),
                offer.getExpiresAt()//expiresAt()
        );
    }

    private DispatchOffer toDomain(DispatchOfferJpaEntity entity) {
        return new DispatchOffer(
                entity.getId(),
                entity.getBreakdownRequestId(),
                entity.getMechanicId(),
               // DispatchOfferStatus.valueOf(entity.getStatus().name()),
                entity.getStatus(),
                entity.getSentAt(),//getCreatedAt(),
                entity.getExpiresAt()
        );
    }

    @Override
    public List<DispatchOffer> findActiveOffersByMechanicId(UUID mechanicId) {
        return repository
                .findByMechanicIdAndStatusAndExpiresAtAfter(
                        mechanicId,
                        DispatchOfferStatus.SENT,
                        LocalDateTime.now()
                )
                .stream()
                .map(DispatchOfferMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<DispatchOffer> findById(UUID offerId) {
        return repository.findById(offerId)
                .map(DispatchOfferMapper::toDomain);
    }

    @Override
    public List<DispatchOffer> findExpiredSentOffers() {
        return repository.findByStatusAndExpiresAtBefore(
                        DispatchOfferStatus.SENT,
                        LocalDateTime.now()
                )
                .stream()
                .map(DispatchOfferMapper::toDomain)
                .toList();
    }

    @Override
    public boolean hasActiveOffers(UUID breakdownRequestId) {
        return repository.existsByBreakdownRequestIdAndStatusAndExpiresAtAfter(
                breakdownRequestId,
                DispatchOfferStatus.SENT,
                LocalDateTime.now()
        );
    }

    @Override
    public boolean hasAcceptedOffer(UUID breakdownRequestId) {
        return repository.existsByBreakdownRequestIdAndStatus(
                breakdownRequestId,
                DispatchOfferStatus.ACCEPTED
        );
    }

    @Override
    public List<UUID> findMechanicIdsAlreadyOffered(UUID breakdownRequestId) {
        return repository.findByBreakdownRequestId(breakdownRequestId)
                .stream()
                .map(DispatchOfferJpaEntity::getMechanicId)
                .distinct()
                .toList();
    }
}