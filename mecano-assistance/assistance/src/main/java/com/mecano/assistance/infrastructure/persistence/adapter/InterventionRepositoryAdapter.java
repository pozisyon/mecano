package com.mecano.assistance.infrastructure.persistence.adapter;

import com.mecano.assistance.domain.model.Intervention;
import com.mecano.assistance.domain.port.InterventionRepositoryPort;
import com.mecano.assistance.infrastructure.persistence.mapper.InterventionMapper;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataInterventionRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class InterventionRepositoryAdapter implements InterventionRepositoryPort {

    private final SpringDataInterventionRepository repository;

    public InterventionRepositoryAdapter(SpringDataInterventionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Intervention save(Intervention intervention) {
        var entity = InterventionMapper.toEntity(intervention);
        var saved = repository.save(entity);
        return InterventionMapper.toDomain(saved);
    }

    @Override
    public Optional<Intervention> findById(UUID id) {
        return repository.findById(id).map(InterventionMapper::toDomain);
    }

    @Override
    public boolean existsByBreakdownRequestId(UUID breakdownRequestId) {
        return repository.existsByBreakdownRequestId(breakdownRequestId);
    }
}