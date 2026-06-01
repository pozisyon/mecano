package com.mecano.assistance.infrastructure.persistence.mapper;

import com.mecano.assistance.domain.model.Mechanic;
import com.mecano.assistance.domain.valueobject.Location;
import com.mecano.assistance.infrastructure.persistence.entity.MechanicEntity;

public class MechanicMapper {

    public static Mechanic toDomain(MechanicEntity entity) {
        return new Mechanic(
                entity.getId(),
                entity.getFullName(),
                entity.getSpeciality(),
                entity.isAvailable(),
                entity.isApproved(),
                entity.getRating(),
                new Location(entity.getLatitude(), entity.getLongitude())
        );
    }

    public static MechanicEntity toEntity(Mechanic mechanic) {
        MechanicEntity entity = new MechanicEntity();

        entity.setId(mechanic.getId());
        entity.setFullName(mechanic.getFullName());
        entity.setSpeciality(mechanic.getSpeciality());
        entity.setAvailable(mechanic.isAvailable());
        entity.setApproved(mechanic.isApproved());
        entity.setRating(mechanic.getRating());
        entity.setLatitude(mechanic.getCurrentLocation().latitude());
        entity.setLongitude(mechanic.getCurrentLocation().longitude());

        return entity;
    }
}