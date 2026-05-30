package com.mecano.assistance.application.command;

import java.util.UUID;

public record RejectDispatchOfferCommand(
        UUID offerId,
        UUID mechanicId
) {}