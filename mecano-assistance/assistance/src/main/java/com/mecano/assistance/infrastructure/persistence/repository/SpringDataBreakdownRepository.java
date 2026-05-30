package com.mecano.assistance.infrastructure.persistence.repository;

import com.mecano.assistance.domain.model.BreakdownStatus;
import com.mecano.assistance.infrastructure.persistence.entity.BreakdownRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataBreakdownRepository
        extends JpaRepository<BreakdownRequestEntity, UUID> {
    List<BreakdownRequestEntity> findByStatus(BreakdownStatus status);
}