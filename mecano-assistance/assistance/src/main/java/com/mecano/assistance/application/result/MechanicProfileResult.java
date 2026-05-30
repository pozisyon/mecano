package com.mecano.assistance.application.result;

import java.util.UUID;

public record MechanicProfileResult(
        UUID id,
        UUID userId,
        String fullName,
        String speciality,
        boolean available,
        boolean approved,
        double rating,
        double latitude,
        double longitude
) {}