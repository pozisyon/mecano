package com.mecano.assistance.application.command;

import com.mecano.assistance.domain.valueobject.Money;

import java.util.UUID;

public record PayInterventionCommand(
        UUID interventionId,
        Money amount,
        String method
) {
}