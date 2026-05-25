package com.mecano.assistance.interfaces.rest.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AcceptInterventionDto(
        @NotNull UUID breakdownRequestId,
        @NotNull UUID mechanicId
) {
}