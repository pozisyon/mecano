package com.mecano.assistance.domain.port;

import com.mecano.assistance.domain.model.Mechanic;
import com.mecano.assistance.domain.valueobject.Location;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MechanicRepositoryPort {

    List<Mechanic> findAvailableMechanicsNear(Location location);

    Optional<Mechanic> findById(UUID id);
    Mechanic save(Mechanic mechanic);

    List<Mechanic> findPendingApproval();
}