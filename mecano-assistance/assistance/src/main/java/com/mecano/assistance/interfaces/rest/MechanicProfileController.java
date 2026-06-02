package com.mecano.assistance.interfaces.rest;

import com.mecano.assistance.application.command.CreateMechanicProfileCommand;
import com.mecano.assistance.application.usecase.*;
import com.mecano.assistance.domain.model.InterventionStatus;
import com.mecano.assistance.domain.model.Role;
import com.mecano.assistance.domain.port.RealtimeNotificationPort;
import com.mecano.assistance.domain.port.UserLookupPort;
import com.mecano.assistance.domain.valueobject.Location;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataBreakdownRepository;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataInterventionRepository;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataMechanicRepository;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataUserRepository;
import com.mecano.assistance.interfaces.rest.dto.CreateMechanicProfileDto;
import com.mecano.assistance.interfaces.rest.dto.UpdateLocationDto;
import com.mecano.assistance.interfaces.rest.exception.BusinessException;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;
import com.mecano.assistance.interfaces.rest.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mechanics")
public class MechanicProfileController {

    private final CreateMechanicProfileUseCase createMechanicProfileUseCase;
    private final GetMyMechanicProfileUseCase getMyMechanicProfileUseCase;
    private final UpdateMechanicAvailabilityUseCase updateMechanicAvailabilityUseCase;
    private final UpdateMechanicLocationUseCase updateMechanicLocationUseCase;

    private final SpringDataUserRepository userRepository;
    private final SpringDataInterventionRepository interventionRepository;
    private final SpringDataBreakdownRepository breakdownRepository;
    private final RealtimeNotificationPort realtimeNotificationPort;
    private final UserLookupPort userLookupPort;
    private final GetMyDispatchOffersUseCase getMyDispatchOffersUseCase;
    private final SpringDataMechanicRepository mechanicRepository;
    private final GetMechanicInterventionHistoryUseCase getMechanicInterventionHistoryUseCase;

    public MechanicProfileController(
            CreateMechanicProfileUseCase createMechanicProfileUseCase,
            GetMyMechanicProfileUseCase getMyMechanicProfileUseCase,
            UpdateMechanicAvailabilityUseCase updateMechanicAvailabilityUseCase,
            UpdateMechanicLocationUseCase updateMechanicLocationUseCase,
            SpringDataUserRepository userRepository,
            SpringDataInterventionRepository interventionRepository,
            SpringDataBreakdownRepository breakdownRepository,
            @Qualifier("webSocketRealtimeNotificationAdapter")RealtimeNotificationPort realtimeNotificationPort,
            UserLookupPort userLookupPort,
            GetMyDispatchOffersUseCase getMyDispatchOffersUseCase,
            SpringDataMechanicRepository mechanicRepository,
            GetMechanicInterventionHistoryUseCase getMechanicInterventionHistoryUseCase
    ) {
        this.createMechanicProfileUseCase = createMechanicProfileUseCase;
        this.getMyMechanicProfileUseCase = getMyMechanicProfileUseCase;
        this.updateMechanicAvailabilityUseCase = updateMechanicAvailabilityUseCase;
        this.updateMechanicLocationUseCase = updateMechanicLocationUseCase;
        this.userRepository = userRepository;
        this.interventionRepository = interventionRepository;
        this.breakdownRepository = breakdownRepository;
        this.realtimeNotificationPort = realtimeNotificationPort;
        this.userLookupPort = userLookupPort;
        this.getMyDispatchOffersUseCase = getMyDispatchOffersUseCase;
        this.mechanicRepository = mechanicRepository;
        this.getMechanicInterventionHistoryUseCase = getMechanicInterventionHistoryUseCase;
    }

    @PostMapping("/profile")
    public ApiResponse<?> createProfile(
            @Valid @RequestBody CreateMechanicProfileDto dto,
            Authentication authentication
    ) {
        var user = getAuthenticatedUser(authentication);

        if (user.getRole() != Role.MECHANIC && user.getRole() != Role.GARAGE_ADMIN) {
            throw new BusinessException("Only mechanics or garage admins can create mechanic profile");
        }

        var result = createMechanicProfileUseCase.execute(
                new CreateMechanicProfileCommand(
                        user.getId(),
                        user.getFullName(),
                        dto.speciality(),
                        new Location(dto.latitude(), dto.longitude())
                )
        );

        return ApiResponse.success("Mechanic profile created successfully", result);
    }

    @GetMapping("/me")
    public ApiResponse<?> myProfile(Authentication authentication) {
        var user = getAuthenticatedUser(authentication);

        return ApiResponse.success(
                "Mechanic profile retrieved successfully",
                getMyMechanicProfileUseCase.execute(user.getId())
        );
    }

    @PatchMapping("/availability")
    public ApiResponse<?> updateAvailability(
            @RequestParam boolean available,
            Authentication authentication
    ) {
        var user = getAuthenticatedUser(authentication);

        var result = updateMechanicAvailabilityUseCase.execute(
                user.getId(),
                available
        );

        return ApiResponse.success("Mechanic availability updated successfully", result);
    }

    @PatchMapping("/location")
    public ApiResponse<?> updateLocation(
            @Valid @RequestBody UpdateLocationDto dto,
            Authentication authentication
    ) {
        var user = getAuthenticatedUser(authentication);

        var result = updateMechanicLocationUseCase.execute(
                user.getId(),
                new Location(dto.latitude(), dto.longitude())
        );

        notifyDriverIfMechanicHasActiveIntervention(result.id());

        return ApiResponse.success("Mechanic location updated successfully", result);
    }

    @GetMapping("/offers")
    public ApiResponse<?> myOffers(Authentication authentication) {
        var user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));

        var mechanic = mechanicRepository.findByUserId(user.getId())
                .orElseThrow(() -> new NotFoundException("Mechanic profile not found"));

        return ApiResponse.success(
                "Active dispatch offers retrieved successfully",
                getMyDispatchOffersUseCase.execute(mechanic.getId())
        );
    }

    private void notifyDriverIfMechanicHasActiveIntervention(java.util.UUID mechanicId) {
        var activeStatuses = List.of(
                InterventionStatus.ACCEPTED,
                InterventionStatus.ON_THE_WAY,
                InterventionStatus.ARRIVED,
                InterventionStatus.IN_PROGRESS
        );

        interventionRepository
                .findFirstByMechanicIdAndStatusIn(mechanicId, activeStatuses)
                .ifPresent(intervention -> {
                    breakdownRepository.findById(intervention.getBreakdownRequestId())
                            .ifPresent(breakdown -> {
                                userLookupPort.findEmailById(breakdown.getDriverId())
                                        .ifPresent(driverEmail ->
                                                realtimeNotificationPort.sendToUser(
                                                        driverEmail,
                                                        "MECHANIC_LOCATION_UPDATED",
                                                        null
                                                )
                                        );
                            });
                });
    }

    private com.mecano.assistance.infrastructure.persistence.entity.UserEntity getAuthenticatedUser(
            Authentication authentication
    ) {
        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    @GetMapping("/interventions/history")
    public ApiResponse<?> interventionHistory(
            Authentication authentication
    ) {
        var user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() ->
                        new NotFoundException("User not found"));

        var mechanic = mechanicRepository.findByUserId(user.getId())
                .orElseThrow(() ->
                        new NotFoundException("Mechanic profile not found"));

        return ApiResponse.success(
                "Mechanic intervention history retrieved successfully",
                getMechanicInterventionHistoryUseCase.execute(
                        mechanic.getId()
                )
        );
    }
}