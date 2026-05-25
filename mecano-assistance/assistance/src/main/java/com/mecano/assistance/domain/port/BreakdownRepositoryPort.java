package com.mecano.assistance.domain.port;

import com.mecano.assistance.domain.model.BreakdownRequest;

import java.util.Optional;
import java.util.UUID;

public interface BreakdownRepositoryPort {

    BreakdownRequest save(BreakdownRequest request);

    Optional<BreakdownRequest> findById(UUID id);
}