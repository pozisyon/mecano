package com.mecano.assistance.application.result;

import com.mecano.assistance.domain.model.PaymentStatus;

import java.util.UUID;

public record PayInterventionResult(
        UUID paymentId,
        UUID interventionId,
        PaymentStatus paymentStatus
) {
}