package com.mecano.assistance.infrastructure.persistence.adapter;

import com.mecano.assistance.domain.model.MechanicProfile;
import com.mecano.assistance.domain.port.MechanicProfileRepositoryPort;
import com.mecano.assistance.infrastructure.persistence.mapper.MechanicProfileMapper;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataMechanicRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MechanicProfileRepositoryAdapter
        implements MechanicProfileRepositoryPort {

    private final SpringDataMechanicRepository repository;

    public MechanicProfileRepositoryAdapter(
            SpringDataMechanicRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public MechanicProfile save(MechanicProfile profile) {
        var entity = MechanicProfileMapper.toEntity(profile);
        var saved = repository.save(entity);
        System.out.println("DOMAIN USER ID = " + profile.getUserId());

        //var entity = MechanicProfileMapper.toEntity(profile);

        System.out.println("ENTITY USER ID = " + entity.getUserId());
        return MechanicProfileMapper.toDomain(saved);
    }

    @Override
    public Optional<MechanicProfile> findById(UUID id) {
        return repository.findById(id)
                .map(MechanicProfileMapper::toDomain);
    }

    @Override
    public Optional<MechanicProfile> findByUserId(UUID userId) {
        return repository.findByUserId(userId)
                .map(MechanicProfileMapper::toDomain);
    }

    @Override
    public boolean existsByUserId(UUID userId) {
        return repository.existsByUserId(userId);
    }

    @Override
    public List<MechanicProfile> findPendingApproval() {
        return repository.findByApprovedFalse()
                .stream()
                .map(MechanicProfileMapper::toDomain)
                .toList();
    }
}