package com.mecano.assistance.infrastructure.persistence.repository;

import com.mecano.assistance.domain.model.DispatchOfferStatus;
import com.mecano.assistance.infrastructure.persistence.entity.DispatchOfferJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataDispatchOfferRepository
        extends JpaRepository<DispatchOfferJpaEntity, UUID> {

    Optional<DispatchOfferJpaEntity> findByBreakdownRequestIdAndMechanicIdAndStatus(
            UUID breakdownRequestId,
            UUID mechanicId,
            DispatchOfferStatus status
    );

    List<DispatchOfferJpaEntity> findByBreakdownRequestId(UUID breakdownRequestId);

    List<DispatchOfferJpaEntity> findByMechanicIdAndStatusAndExpiresAtAfter(
            UUID mechanicId,
            DispatchOfferStatus status,
            LocalDateTime now
    );

   /* List<DispatchOfferJpaEntity> findByStatusAndExpiresAtBefore(
            DispatchOfferStatus status,
            LocalDateTime now
    );*/
    boolean existsByBreakdownRequestIdAndStatusAndExpiresAtAfter(
            UUID breakdownRequestId,
            DispatchOfferStatus status,
            LocalDateTime now
    );

    boolean existsByBreakdownRequestIdAndStatus(
            UUID breakdownRequestId,
            DispatchOfferStatus status
    );
    List<DispatchOfferJpaEntity> findByStatusAndExpiresAtBefore(
            DispatchOfferStatus status,
            LocalDateTime now
    );

   // List<DispatchOfferJpaEntity> findByBreakdownRequestId(UUID breakdownRequestId);
}