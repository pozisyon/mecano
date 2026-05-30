package com.mecano.assistance.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehicles")
public class VehicleEntity {

    @Id
    private UUID id;

    private UUID ownerId;

    private String brand;
    private String model;
    private String plateNumber;
    private Integer year;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
    }

    // getters / setters
}