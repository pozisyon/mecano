package com.mecano.assistance.application.command;

import java.util.UUID;

public record AcceptDispatchOfferCommand(
        UUID offerId,
        UUID mechanicId
) {}