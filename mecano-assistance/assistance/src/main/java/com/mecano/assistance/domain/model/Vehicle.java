package com.mecano.assistance.domain.model;

import java.time.Year;
import java.util.UUID;

public class Vehicle {

    private final UUID id;
    private final UUID ownerId;
    private final String brand;
    private final String model;
    private final String plateNumber;
    private final int year;

    private Vehicle(
            UUID id,
            UUID ownerId,
            String brand,
            String model,
            String plateNumber,
            int year
    ) {
        if (id == null) throw new IllegalArgumentException("Vehicle id is required");
        if (ownerId == null) throw new IllegalArgumentException("Owner is required");
        if (brand == null || brand.isBlank()) throw new IllegalArgumentException("Brand is required");
        if (model == null || model.isBlank()) throw new IllegalArgumentException("Model is required");
        if (plateNumber == null || plateNumber.isBlank()) throw new IllegalArgumentException("Plate number is required");

        int currentYear = Year.now().getValue();
        if (year < 1950 || year > currentYear + 1) {
            throw new IllegalArgumentException("Invalid vehicle year");
        }

        this.id = id;
        this.ownerId = ownerId;
        this.brand = brand;
        this.model = model;
        this.plateNumber = plateNumber.toUpperCase();
        this.year = year;
    }

    public static Vehicle create(
            UUID ownerId,
            String brand,
            String model,
            String plateNumber,
            int year
    ) {
        return new Vehicle(
                UUID.randomUUID(),
                ownerId,
                brand,
                model,
                plateNumber,
                year
        );
    }

    public static Vehicle restore(
            UUID id,
            UUID ownerId,
            String brand,
            String model,
            String plateNumber,
            int year
    ) {
        return new Vehicle(id, ownerId, brand, model, plateNumber, year);
    }

    public UUID getId() { return id; }
    public UUID getOwnerId() { return ownerId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getPlateNumber() { return plateNumber; }
    public int getYear() { return year; }
}