package com.mecano.assistance.infrastructure.persistence.mapper;

import com.mecano.assistance.domain.model.BreakdownRequest;
import com.mecano.assistance.domain.valueobject.Location;
import com.mecano.assistance.infrastructure.persistence.entity.BreakdownRequestEntity;

public class BreakdownRequestMapper {

    public static BreakdownRequestEntity toEntity(BreakdownRequest request) {
        BreakdownRequestEntity entity = new BreakdownRequestEntity();

        entity.setId(request.getId());
        entity.setDriverId(request.getDriverId());
        entity.setVehicleId(request.getVehicleId());
        entity.setType(request.getType());
        entity.setDescription(request.getDescription());
        entity.setLatitude(request.getLocation().latitude());
        entity.setLongitude(request.getLocation().longitude());
        entity.setStatus(request.getStatus());
        entity.setCreatedAt(request.getCreatedAt());
        entity.setDispatchAttempts(request.getDispatchAttempts());

        return entity;
    }

    public static BreakdownRequest toDomain(BreakdownRequestEntity entity) {
        BreakdownRequest request = BreakdownRequest.restore(
                entity.getId(),
                entity.getDriverId(),
                entity.getVehicleId(),
                entity.getType(),
                entity.getDescription(),
                new Location(entity.getLatitude(), entity.getLongitude()),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getDispatchAttempts() != null
                        ? entity.getDispatchAttempts()
                        : 0
        );

        return request;
    }
}