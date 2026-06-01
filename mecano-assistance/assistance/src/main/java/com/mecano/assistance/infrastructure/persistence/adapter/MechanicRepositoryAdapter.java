package com.mecano.assistance.infrastructure.persistence.adapter;

import com.mecano.assistance.domain.model.Mechanic;
import com.mecano.assistance.domain.port.MechanicRepositoryPort;
import com.mecano.assistance.domain.valueobject.Location;
import com.mecano.assistance.infrastructure.persistence.mapper.MechanicMapper;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataMechanicRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MechanicRepositoryAdapter implements MechanicRepositoryPort {

    private final SpringDataMechanicRepository repository;

    public MechanicRepositoryAdapter(SpringDataMechanicRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Mechanic> findAvailableMechanicsNear(Location location) {
        return repository.findByAvailableTrueAndApprovedTrue()
                .stream()
                .map(MechanicMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Mechanic> findById(UUID id) {
        return repository.findById(id)
                .map(MechanicMapper::toDomain);
    }

    @Override
    public Mechanic save(Mechanic mechanic) {
        var saved = repository.save(MechanicMapper.toEntity(mechanic));
        return MechanicMapper.toDomain(saved);
    }

    @Override
    public List<Mechanic> findPendingApproval() {
        return repository.findByApprovedFalse()
                .stream()
                .map(MechanicMapper::toDomain)
                .toList();
    }
}