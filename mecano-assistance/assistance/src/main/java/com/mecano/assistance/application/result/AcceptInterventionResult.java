package com.mecano.assistance.application.result;

import com.mecano.assistance.domain.model.InterventionStatus;

import java.util.UUID;

public record AcceptInterventionResult(
        UUID interventionId,
        UUID breakdownRequestId,
        UUID mechanicId,
        InterventionStatus status
) {
}