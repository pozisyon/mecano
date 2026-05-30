package com.mecano.assistance.interfaces.rest.dto;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AcceptDispatchOfferDto(
        @NotNull UUID offerId
) {}