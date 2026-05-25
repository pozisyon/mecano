package com.mecano.assistance.interfaces.rest.dto;

import com.mecano.assistance.domain.model.InterventionStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateInterventionStatusDto(
        @NotNull InterventionStatus status
) {
}