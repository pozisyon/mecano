package com.mecano.assistance.application.command;

import com.mecano.assistance.domain.valueobject.Location;

import java.util.UUID;

public record CreateMechanicProfileCommand(
        UUID userId,
        String fullName,
        String speciality,
        Location location
) {}