package com.mecano.assistance.interfaces.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateMechanicProfileDto(
        @NotBlank String speciality,
        @NotNull Double latitude,
        @NotNull Double longitude
) {}