package com.mecano.assistance.interfaces.rest.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateLocationDto(
        @NotNull Double latitude,
        @NotNull Double longitude
) {}