package com.mecano.assistance.interfaces.rest;

import com.mecano.assistance.application.command.AcceptInterventionCommand;
import com.mecano.assistance.application.command.UpdateInterventionStatusCommand;
import com.mecano.assistance.application.usecase.AcceptInterventionUseCase;
import com.mecano.assistance.application.usecase.UpdateInterventionStatusUseCase;
import com.mecano.assistance.interfaces.rest.dto.AcceptInterventionDto;
import com.mecano.assistance.interfaces.rest.dto.UpdateInterventionStatusDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/interventions")
public class InterventionController {

    private final AcceptInterventionUseCase acceptInterventionUseCase;
    private final UpdateInterventionStatusUseCase updateInterventionStatusUseCase;

    public InterventionController(
            AcceptInterventionUseCase acceptInterventionUseCase,
            UpdateInterventionStatusUseCase updateInterventionStatusUseCase
    ) {
        this.acceptInterventionUseCase = acceptInterventionUseCase;
        this.updateInterventionStatusUseCase = updateInterventionStatusUseCase;
    }

    @PostMapping("/accept")
    @ResponseStatus(HttpStatus.CREATED)
    public Object accept(@Valid @RequestBody AcceptInterventionDto dto) {
        return acceptInterventionUseCase.execute(
                new AcceptInterventionCommand(
                        dto.breakdownRequestId(),
                        dto.mechanicId()
                )
        );
    }

    @PatchMapping("/{id}/status")
    public Object updateStatus(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateInterventionStatusDto dto
    ) {
        return updateInterventionStatusUseCase.execute(
                new UpdateInterventionStatusCommand(
                        id,
                        dto.status()
                )
        );
    }
}