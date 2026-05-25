package com.mecano.assistance.application.command;

import java.util.UUID;

public record AcceptInterventionCommand(
        UUID breakdownRequestId,
        UUID mechanicId
) {
}