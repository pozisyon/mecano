package com.mecano.assistance.infrastructure.persistence.mapper;

import com.mecano.assistance.domain.model.Vehicle;
import com.mecano.assistance.infrastructure.persistence.entity.VehicleEntity;

public class VehicleMapper {

    public static VehicleEntity toEntity(Vehicle vehicle) {
        VehicleEntity entity = new VehicleEntity();

        entity.setId(vehicle.getId());
        entity.setOwnerId(vehicle.getOwnerId());
        entity.setBrand(vehicle.getBrand());
        entity.setModel(vehicle.getModel());
        entity.setPlateNumber(vehicle.getPlateNumber());
        entity.setYear(vehicle.getYear());

        return entity;
    }

    public static Vehicle toDomain(VehicleEntity entity) {
        return Vehicle.restore(
                entity.getId(),
                entity.getOwnerId(),
                entity.getBrand(),
                entity.getModel(),
                entity.getPlateNumber(),
                entity.getYear()
        );
    }
}