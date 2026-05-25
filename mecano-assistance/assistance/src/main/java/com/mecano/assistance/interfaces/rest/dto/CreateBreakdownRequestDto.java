package com.mecano.assistance.interfaces.rest.dto;

import com.mecano.assistance.domain.model.BreakdownStatus;
import com.mecano.assistance.domain.model.BreakdownType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateBreakdownRequestDto(
        @NotNull UUID driverId,
        @NotNull UUID vehicleId,
        @NotNull BreakdownType type,
        String description,
        @NotNull Double latitude,
        @NotNull Double longitude,
        @NotNull BreakdownStatus status,
        @NotNull LocalDateTime createdAt
) {
}
