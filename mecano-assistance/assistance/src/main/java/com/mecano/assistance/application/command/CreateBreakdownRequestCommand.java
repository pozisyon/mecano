package com.mecano.assistance.application.command;

import com.mecano.assistance.domain.model.BreakdownStatus;
import com.mecano.assistance.domain.model.BreakdownType;
import com.mecano.assistance.domain.valueobject.Location;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateBreakdownRequestCommand(
        UUID driverId,
        UUID vehicleId,
        BreakdownType type,
        String description,
        Location location,
        BreakdownStatus status,
        LocalDateTime createdAt
) {
}