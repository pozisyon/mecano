package com.mecano.assistance.domain.model;

import com.mecano.assistance.domain.valueobject.Location;

import java.util.UUID;

public class MechanicProfile {

    private final UUID id;
    private final UUID userId;
    private final String fullName;
    private final String speciality;
    private boolean available;
    private boolean approved;
    private double rating;
    private Location currentLocation;

    private MechanicProfile(
            UUID id,
            UUID userId,
            String fullName,
            String speciality,
            boolean available,
            boolean approved,
            double rating,
            Location currentLocation
    ) {
        if (id == null) throw new IllegalArgumentException("Mechanic profile id is required");
        if (userId == null) throw new IllegalArgumentException("User id is required");
        if (fullName == null || fullName.isBlank()) throw new IllegalArgumentException("Full name is required");
        if (speciality == null || speciality.isBlank()) throw new IllegalArgumentException("Speciality is required");
        if (currentLocation == null) throw new IllegalArgumentException("Location is required");

        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.speciality = speciality;
        this.available = available;
        this.approved = approved;
        this.rating = rating;
        this.currentLocation = currentLocation;
    }

    public static MechanicProfile create(
            UUID userId,
            String fullName,
            String speciality,
            Location location
    ) {
        return new MechanicProfile(
                UUID.randomUUID(),
                userId,
                fullName,
                speciality,
                true,
                false,
                5.0,
                location
        );
    }

    public static MechanicProfile restore(
            UUID id,
            UUID userId,
            String fullName,
            String speciality,
            boolean available,
            boolean approved,
            double rating,
            Location location
    ) {
        return new MechanicProfile(
                id,
                userId,
                fullName,
                speciality,
                available,
                approved,
                rating,
                location
        );
    }

    public void updateAvailability(boolean available) {
        this.available = available;
    }

    public void updateLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location is required");
        }
        this.currentLocation = location;
    }

    public void approve() {
        this.approved = true;
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getSpeciality() { return speciality; }
    public boolean isAvailable() { return available; }
    public boolean isApproved() { return approved; }
    public double getRating() { return rating; }
    public Location getCurrentLocation() { return currentLocation; }
}