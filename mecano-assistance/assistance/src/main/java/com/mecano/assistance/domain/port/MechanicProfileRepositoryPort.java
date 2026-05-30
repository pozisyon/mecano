package com.mecano.assistance.domain.port;

import com.mecano.assistance.domain.model.MechanicProfile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MechanicProfileRepositoryPort {

    MechanicProfile save(MechanicProfile profile);

    Optional<MechanicProfile> findById(UUID id);

    Optional<MechanicProfile> findByUserId(UUID userId);

    boolean existsByUserId(UUID userId);

    List<MechanicProfile> findPendingApproval();
}