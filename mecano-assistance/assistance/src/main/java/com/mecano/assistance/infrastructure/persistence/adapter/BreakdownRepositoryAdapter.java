package com.mecano.assistance.infrastructure.persistence.adapter;


import com.mecano.assistance.domain.model.BreakdownRequest;
import com.mecano.assistance.domain.port.BreakdownRepositoryPort;
import com.mecano.assistance.infrastructure.persistence.mapper.BreakdownRequestMapper;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataBreakdownRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class BreakdownRepositoryAdapter implements BreakdownRepositoryPort {

    private final SpringDataBreakdownRepository repository;

    public BreakdownRepositoryAdapter(SpringDataBreakdownRepository repository) {
        this.repository = repository;
    }

    @Override
    public BreakdownRequest save(BreakdownRequest request) {
        var entity = BreakdownRequestMapper.toEntity(request);
        var saved = repository.save(entity);
        return BreakdownRequestMapper.toDomain(saved);
    }

    @Override
    public Optional<BreakdownRequest> findById(UUID id) {
        return repository.findById(id)
                .map(BreakdownRequestMapper::toDomain);
    }
}