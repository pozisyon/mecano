package com.mecano.assistance.infrastructure.persistence.mapper;

import com.mecano.assistance.domain.model.Intervention;
import com.mecano.assistance.infrastructure.persistence.entity.InterventionEntity;

public class InterventionMapper {

    public static InterventionEntity toEntity(Intervention intervention) {
        InterventionEntity entity = new InterventionEntity();

        entity.setId(intervention.getId());
        entity.setBreakdownRequestId(intervention.getBreakdownRequestId());
        entity.setMechanicId(intervention.getMechanicId());
        entity.setStatus(intervention.getStatus());
        entity.setAcceptedAt(intervention.getAcceptedAt());
        entity.setCompletedAt(intervention.getCompletedAt());

        return entity;
    }

    public static Intervention toDomain(InterventionEntity entity) {
        return Intervention.restore(
                entity.getId(),
                entity.getBreakdownRequestId(),
                entity.getMechanicId(),
                entity.getStatus(),
                entity.getAcceptedAt(),
                entity.getCompletedAt()
        );
    }
}