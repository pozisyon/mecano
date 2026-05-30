package com.mecano.assistance.interfaces.rest;

import com.mecano.assistance.application.command.AcceptInterventionCommand;
import com.mecano.assistance.application.command.RejectDispatchOfferCommand;
import com.mecano.assistance.application.command.UpdateInterventionStatusCommand;
import com.mecano.assistance.application.usecase.AcceptInterventionUseCase;
import com.mecano.assistance.application.usecase.RejectDispatchOfferUseCase;
import com.mecano.assistance.application.usecase.UpdateInterventionStatusUseCase;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataMechanicRepository;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataUserRepository;
import com.mecano.assistance.interfaces.rest.dto.AcceptInterventionDto;
import com.mecano.assistance.interfaces.rest.dto.RejectDispatchOfferDto;
import com.mecano.assistance.interfaces.rest.dto.UpdateInterventionStatusDto;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;
import com.mecano.assistance.interfaces.rest.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/interventions")
public class InterventionController {

    private final AcceptInterventionUseCase acceptInterventionUseCase;
    private final UpdateInterventionStatusUseCase updateInterventionStatusUseCase;
    private final RejectDispatchOfferUseCase rejectDispatchOfferUseCase;
    private final SpringDataUserRepository userRepository;
    private final SpringDataMechanicRepository mechanicRepository;
    public InterventionController(
            AcceptInterventionUseCase acceptInterventionUseCase,
            UpdateInterventionStatusUseCase updateInterventionStatusUseCase,
            RejectDispatchOfferUseCase rejectDispatchOfferUseCase,
            SpringDataUserRepository userRepository,
            SpringDataMechanicRepository mechanicRepository
    ) {
        this.acceptInterventionUseCase = acceptInterventionUseCase;
        this.updateInterventionStatusUseCase = updateInterventionStatusUseCase;
        this.rejectDispatchOfferUseCase = rejectDispatchOfferUseCase;
        this.userRepository = userRepository;
        this.mechanicRepository = mechanicRepository;

    }

    @PostMapping("/accept")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<?> accept(@Valid @RequestBody AcceptInterventionDto dto) {
        var result = acceptInterventionUseCase.execute(
                new AcceptInterventionCommand(
                        dto.breakdownRequestId(),
                        dto.mechanicId()
                )
        );

        return ApiResponse.success("Intervention accepted successfully", result);
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<?> updateStatus(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateInterventionStatusDto dto
    ) {
        var result = updateInterventionStatusUseCase.execute(
                new UpdateInterventionStatusCommand(
                        id,
                        dto.status()
                )
        );

        return ApiResponse.success("Intervention status updated successfully", result);
    }
    @PostMapping("/reject-offer")
    public ApiResponse<?> rejectOffer(
            @Valid @RequestBody RejectDispatchOfferDto dto,
            Authentication authentication
    ) {
        var user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));

        var mechanic = mechanicRepository.findByUserId(user.getId())
                .orElseThrow(() -> new NotFoundException("Mechanic profile not found"));

        rejectDispatchOfferUseCase.execute(
                new RejectDispatchOfferCommand(dto.offerId(), mechanic.getId())
        );

        return ApiResponse.success(
                "Dispatch offer rejected successfully",
                null
        );
    }
}