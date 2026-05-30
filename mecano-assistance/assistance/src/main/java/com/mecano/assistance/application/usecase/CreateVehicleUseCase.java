package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.command.CreateVehicleCommand;
import com.mecano.assistance.application.result.CreateVehicleResult;
import com.mecano.assistance.domain.model.Vehicle;
import com.mecano.assistance.domain.port.VehicleRepositoryPort;
import com.mecano.assistance.interfaces.rest.exception.BusinessException;

public class CreateVehicleUseCase {

    private final VehicleRepositoryPort vehicleRepository;

    public CreateVehicleUseCase(VehicleRepositoryPort vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public CreateVehicleResult execute(CreateVehicleCommand command) {
        String normalizedPlate = command.plateNumber().toUpperCase();

        if (vehicleRepository.existsByOwnerIdAndPlateNumber(
                command.ownerId(),
                normalizedPlate
        )) {
            throw new BusinessException("Vehicle plate number already registered for this owner");
        }

        Vehicle vehicle = Vehicle.create(
                command.ownerId(),
                command.brand(),
                command.model(),
                normalizedPlate,
                command.year()
        );

        Vehicle saved = vehicleRepository.save(vehicle);

        return new CreateVehicleResult(
                saved.getId(),
                saved.getOwnerId(),
                saved.getBrand(),
                saved.getModel(),
                saved.getPlateNumber(),
                saved.getYear()
        );
    }
}