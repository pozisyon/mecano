package com.mecano.assistance.infrastructure.persistence.repository;

import com.mecano.assistance.infrastructure.persistence.entity.InterventionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataInterventionRepository
        extends JpaRepository<InterventionEntity, UUID> {

    boolean existsByBreakdownRequestId(UUID breakdownRequestId);
}