package com.mecano.assistance.infrastructure.persistence.repository;

import com.mecano.assistance.infrastructure.persistence.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataVehicleRepository extends JpaRepository<VehicleEntity, UUID> {

    List<VehicleEntity> findByOwnerId(UUID ownerId);

    boolean existsByIdAndOwnerId(UUID id, UUID ownerId);

    boolean existsByOwnerIdAndPlateNumber(UUID ownerId, String plateNumber);
}