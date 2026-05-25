package com.mecano.assistance.application.result;

import com.mecano.assistance.domain.model.BreakdownStatus;

import java.util.UUID;

public record CreateBreakdownRequestResult(
        UUID requestId,
        BreakdownStatus status
) {
}