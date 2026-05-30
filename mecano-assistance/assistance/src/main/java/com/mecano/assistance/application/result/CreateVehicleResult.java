package com.mecano.assistance.application.result;

import java.util.UUID;

public record CreateVehicleResult(
        UUID vehicleId,
        UUID ownerId,
        String brand,
        String model,
        String plateNumber,
        int year
) {}