package com.mecano.assistance.domain.port;

import com.mecano.assistance.domain.model.Intervention;

import java.util.Optional;
import java.util.UUID;

public interface InterventionRepositoryPort {

    Intervention save(Intervention intervention);

    Optional<Intervention> findById(UUID id);

    boolean existsByBreakdownRequestId(UUID breakdownRequestId);
}