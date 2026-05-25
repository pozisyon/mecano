package com.mecano.assistance.application.usecase;

import com.mecano.assistance.application.command.CreateBreakdownRequestCommand;
import com.mecano.assistance.application.result.CreateBreakdownRequestResult;
import com.mecano.assistance.domain.model.BreakdownRequest;
import com.mecano.assistance.domain.port.BreakdownRepositoryPort;

public class CreateBreakdownRequestUseCase {

    private final BreakdownRepositoryPort breakdownRepository;

    public CreateBreakdownRequestUseCase(BreakdownRepositoryPort breakdownRepository) {
        this.breakdownRepository = breakdownRepository;
    }

    public CreateBreakdownRequestResult execute(CreateBreakdownRequestCommand command) {
        BreakdownRequest request = BreakdownRequest.create(
                command.driverId(),
                command.vehicleId(),
                command.type(),
                command.description(),
                command.location(),command.status(),command.createdAt()

        );

        BreakdownRequest saved = breakdownRepository.save(request);

        return new CreateBreakdownRequestResult(
                saved.getId(),
                saved.getStatus()
        );
    }
}