package com.mecano.assistance.infrastructure.persistence.repository;

import com.mecano.assistance.infrastructure.persistence.entity.BreakdownRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataBreakdownRepository
        extends JpaRepository<BreakdownRequestEntity, UUID> {
}