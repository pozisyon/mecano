package com.mecano.assistance.application.command;

import java.util.UUID;

public record CreateVehicleCommand(
        UUID ownerId,
        String brand,
        String model,
        String plateNumber,
        int year
) {}