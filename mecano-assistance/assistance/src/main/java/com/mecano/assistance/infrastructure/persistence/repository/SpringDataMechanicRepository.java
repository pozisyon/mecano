package com.mecano.assistance.infrastructure.persistence.repository;

import com.mecano.assistance.infrastructure.persistence.entity.MechanicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataMechanicRepository
        extends JpaRepository<MechanicEntity, UUID> {

    List<MechanicEntity> findByAvailableTrueAndApprovedTrue();

    Optional<MechanicEntity> findByUserId(UUID userId);

    boolean existsByUserId(UUID userId);

    List<MechanicEntity> findByApprovedFalse();
}