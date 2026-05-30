package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.command.CreateBreakdownRequestCommand;
import com.mecano.assistance.application.result.CreateBreakdownRequestResult;
import com.mecano.assistance.domain.model.BreakdownRequest;
import com.mecano.assistance.domain.port.BreakdownRepositoryPort;
import com.mecano.assistance.domain.port.VehicleRepositoryPort;
import com.mecano.assistance.interfaces.rest.exception.BusinessException;

public class CreateBreakdownRequestUseCase {

    private final BreakdownRepositoryPort breakdownRepository;
    private final VehicleRepositoryPort vehicleRepository;

    public CreateBreakdownRequestUseCase(BreakdownRepositoryPort breakdownRepository, VehicleRepositoryPort vehicleRepositoryPort) {
        this.breakdownRepository = breakdownRepository;
        this.vehicleRepository = vehicleRepositoryPort;

    }

    public CreateBreakdownRequestResult execute(CreateBreakdownRequestCommand command) {

        boolean vehicleBelongsToDriver = vehicleRepository.existsByIdAndOwnerId(
                command.vehicleId(),
                command.driverId()
        );

        if (!vehicleBelongsToDriver) {
            throw new BusinessException("Vehicle does not belong to authenticated driver");
        }

        BreakdownRequest request = BreakdownRequest.create(
                command.driverId(),
                command.vehicleId(),
                command.type(),
                command.description(),
                command.location(),
                command.status(),
                command.createdAt(),
                0

        );

        BreakdownRequest saved = breakdownRepository.save(request);

        return new CreateBreakdownRequestResult(
                saved.getId(),
                saved.getStatus()
        );
    }
}