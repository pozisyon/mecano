package com.mecano.assistance.interfaces.rest;

import com.mecano.assistance.application.command.CreateVehicleCommand;
import com.mecano.assistance.application.usecase.CreateVehicleUseCase;
import com.mecano.assistance.application.usecase.GetMyVehiclesUseCase;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataUserRepository;
import com.mecano.assistance.interfaces.rest.dto.CreateVehicleDto;
import com.mecano.assistance.interfaces.rest.exception.NotFoundException;
import com.mecano.assistance.interfaces.rest.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final CreateVehicleUseCase createVehicleUseCase;
    private final GetMyVehiclesUseCase getMyVehiclesUseCase;
    private final SpringDataUserRepository userRepository;

    public VehicleController(
            CreateVehicleUseCase createVehicleUseCase,
            GetMyVehiclesUseCase getMyVehiclesUseCase,
            SpringDataUserRepository userRepository
    ) {
        this.createVehicleUseCase = createVehicleUseCase;
        this.getMyVehiclesUseCase = getMyVehiclesUseCase;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ApiResponse<?> create(
            @Valid @RequestBody CreateVehicleDto dto,
            Authentication authentication
    ) {
        var user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));

        var result = createVehicleUseCase.execute(
                new CreateVehicleCommand(
                        user.getId(),
                        dto.brand(),
                        dto.model(),
                        dto.plateNumber(),
                        dto.year()
                )
        );

        return ApiResponse.success("Vehicle created successfully", result);
    }

    @GetMapping
    public ApiResponse<?> myVehicles(Authentication authentication) {
        var user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new NotFoundException("User not found"));

        return ApiResponse.success(
                "Vehicles retrieved successfully",
                getMyVehiclesUseCase.execute(user.getId())
        );
    }
}