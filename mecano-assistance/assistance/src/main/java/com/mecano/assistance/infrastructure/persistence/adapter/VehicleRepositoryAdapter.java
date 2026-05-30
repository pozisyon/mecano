package com.mecano.assistance.infrastructure.persistence.adapter;

import com.mecano.assistance.domain.model.Vehicle;
import com.mecano.assistance.domain.port.VehicleRepositoryPort;
import com.mecano.assistance.infrastructure.persistence.mapper.VehicleMapper;
import com.mecano.assistance.infrastructure.persistence.repository.SpringDataVehicleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class VehicleRepositoryAdapter implements VehicleRepositoryPort {

    private final SpringDataVehicleRepository repository;

    public VehicleRepositoryAdapter(SpringDataVehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        var saved = repository.save(VehicleMapper.toEntity(vehicle));
        return VehicleMapper.toDomain(saved);
    }

    @Override
    public Optional<Vehicle> findById(UUID id) {
        return repository.findById(id).map(VehicleMapper::toDomain);
    }

    @Override
    public List<Vehicle> findByOwnerId(UUID ownerId) {
        return repository.findByOwnerId(ownerId)
                .stream()
                .map(VehicleMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByIdAndOwnerId(UUID vehicleId, UUID ownerId) {
        return repository.existsByIdAndOwnerId(vehicleId, ownerId);
    }

    @Override
    public boolean existsByOwnerIdAndPlateNumber(UUID ownerId, String plateNumber) {
        return repository.existsByOwnerIdAndPlateNumber(ownerId, plateNumber);
    }
}