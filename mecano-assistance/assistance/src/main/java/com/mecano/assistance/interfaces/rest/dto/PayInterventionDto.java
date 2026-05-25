package com.mecano.assistance.interfaces.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record PayInterventionDto(
        @NotNull UUID interventionId,
        @NotNull BigDecimal amount,
        @NotBlank String currency,
        @NotBlank String method
) {
}