package com.mecano.assistance.application.result;

import com.mecano.assistance.domain.model.InterventionStatus;

import java.util.UUID;

public record UpdateInterventionStatusResult(
        UUID interventionId,
        InterventionStatus status
) {
}