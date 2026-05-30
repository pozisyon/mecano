package com.mecano.assistance.application.usecase;

import com.mecano.assistance.domain.model.Vehicle;
import com.mecano.assistance.domain.port.VehicleRepositoryPort;

import java.util.List;
import java.util.UUID;

public class GetMyVehiclesUseCase {

    private final VehicleRepositoryPort vehicleRepository;

    public GetMyVehiclesUseCase(VehicleRepositoryPort vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> execute(UUID ownerId) {
        return vehicleRepository.findByOwnerId(ownerId);
    }
}