package com.mecano.assistance.interfaces.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateVehicleDto(
        @NotBlank String brand,
        @NotBlank String model,
        @NotBlank String plateNumber,
        @NotNull Integer year
) {}