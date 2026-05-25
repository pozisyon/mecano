package com.mecano.assistance.domain.model;

import com.mecano.assistance.domain.valueobject.Location;

import java.util.UUID;

public class Mechanic {

    private final UUID id;
    private final String fullName;
    private final String speciality;
    private final boolean available;
    private final double rating;
    private final Location currentLocation;

    public Mechanic(
            UUID id,
            String fullName,
            String speciality,
            boolean available,
            double rating,
            Location currentLocation
    ) {
        if (id == null) throw new IllegalArgumentException("Mechanic id is required");
        if (currentLocation == null) throw new IllegalArgumentException("Mechanic location is required");

        this.id = id;
        this.fullName = fullName;
        this.speciality = speciality;
        this.available = available;
        this.rating = rating;
        this.currentLocation = currentLocation;
    }

    public UUID getId() { return id; }
    public String getFullName() { return fullName; }
    public String getSpeciality() { return speciality; }
    public boolean isAvailable() { return available; }
    public double getRating() { return rating; }
    public Location getCurrentLocation() { return currentLocation; }
}