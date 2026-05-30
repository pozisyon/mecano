package com.mecano.assistance.domain.port;

import com.mecano.assistance.domain.model.Vehicle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleRepositoryPort {

    Vehicle save(Vehicle vehicle);

    Optional<Vehicle> findById(UUID id);

    List<Vehicle> findByOwnerId(UUID ownerId);

    boolean existsByIdAndOwnerId(UUID vehicleId, UUID ownerId);

    boolean existsByOwnerIdAndPlateNumber(UUID ownerId, String plateNumber);
}