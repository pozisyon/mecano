package com.mecano.assistance.infrastructure.persistence.mapper;

import com.mecano.assistance.domain.model.DispatchOffer;
import com.mecano.assistance.domain.model.DispatchOfferStatus;
//import com.mecano.assistance.infrastructure.persistence.entity.DispatchOfferEntity;
import com.mecano.assistance.infrastructure.persistence.entity.DispatchOfferJpaEntity;

public class DispatchOfferMapper {

    public static DispatchOfferJpaEntity toEntity(DispatchOffer offer) {
        DispatchOfferJpaEntity entity = new DispatchOfferJpaEntity();

        entity.setId(offer.getId());
        entity.setBreakdownRequestId(offer.getBreakdownRequestId());
        entity.setMechanicId(offer.getMechanicId());
       // entity.setStatus(offer.getStatus());//pourquoi erreur
       // DispatchOfferStatus.valueOf(entity.getStatus());
        entity.getStatus();
        entity.setSentAt(offer.getSentAt());
        entity.setExpiresAt(offer.getExpiresAt());

        return entity;
    }

    public static DispatchOffer toDomain(DispatchOfferJpaEntity entity) {
        return DispatchOffer.restore(
                entity.getId(),
                entity.getBreakdownRequestId(),
                entity.getMechanicId(),
                //DispatchOfferStatus.valueOf(entity.getStatus()),
                 entity.getStatus(),// pourquoi erreur
                entity.getSentAt(),
                entity.getExpiresAt()
        );
    }


}