package com.mecano.assistance.domain.port;

import com.mecano.assistance.domain.model.Intervention;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InterventionRepositoryPort {

    Intervention save(Intervention intervention);

    Optional<Intervention> findById(UUID id);

    boolean existsByBreakdownRequestId(UUID breakdownRequestId);

    List<Intervention> findByBreakdownRequestIds(List<UUID> breakdownRequestIds);

    List<Intervention> findByMechanicId(UUID mechanicId);
}