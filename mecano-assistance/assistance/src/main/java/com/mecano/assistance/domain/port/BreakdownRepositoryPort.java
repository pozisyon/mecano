package com.mecano.assistance.domain.port;

import com.mecano.assistance.domain.model.BreakdownRequest;
import com.mecano.assistance.domain.model.BreakdownStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BreakdownRepositoryPort {

    BreakdownRequest save(BreakdownRequest request);

    Optional<BreakdownRequest> findById(UUID id);
    List<BreakdownRequest> findByStatus(BreakdownStatus status);
    List<BreakdownRequest> findByDriverId(UUID driverId);
}