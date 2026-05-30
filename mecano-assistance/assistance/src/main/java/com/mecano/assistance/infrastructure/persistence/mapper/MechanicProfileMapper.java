package com.mecano.assistance.infrastructure.persistence.mapper;

import com.mecano.assistance.domain.model.MechanicProfile;
import com.mecano.assistance.domain.valueobject.Location;
import com.mecano.assistance.infrastructure.persistence.entity.MechanicEntity;

public class MechanicProfileMapper {

    public static MechanicEntity toEntity(MechanicProfile profile) {
        MechanicEntity entity = new MechanicEntity();

        entity.setId(profile.getId());
       // entity.setUserId(profile.getUserId());
        entity.setFullName(profile.getFullName());
        entity.setSpeciality(profile.getSpeciality());
        entity.setAvailable(profile.isAvailable());
       // entity.setApproved(profile.isApproved());
        entity.setRating(profile.getRating());
        entity.setLatitude(profile.getCurrentLocation().latitude());
        entity.setLongitude(profile.getCurrentLocation().longitude());

        return entity;
    }

    public static MechanicProfile toDomain(MechanicEntity entity) {
        return MechanicProfile.restore(
                entity.getId(),
                entity.getUserId(),
                entity.getFullName(),
                entity.getSpeciality(),
                entity.isAvailable(),
                entity.isApproved(),
                entity.getRating(),
                new Location(entity.getLatitude(), entity.getLongitude())
        );
    }
}