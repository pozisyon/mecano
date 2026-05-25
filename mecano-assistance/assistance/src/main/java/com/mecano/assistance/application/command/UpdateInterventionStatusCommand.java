package com.mecano.assistance.application.command;

import com.mecano.assistance.domain.model.InterventionStatus;

import java.util.UUID;

public record UpdateInterventionStatusCommand(
        UUID interventionId,
        InterventionStatus newStatus
) {
}