package com.mecano.assistance.interfaces.rest.dto;

import com.mecano.assistance.domain.model.BreakdownType;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateBreakdownRequestDto(
        @NotNull UUID vehicleId,
        @NotNull BreakdownType type,
        String description,
        @NotNull Double latitude,
        @NotNull Double longitude
) {}