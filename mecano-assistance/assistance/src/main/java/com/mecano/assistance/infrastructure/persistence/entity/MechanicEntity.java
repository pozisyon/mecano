package com.mecano.assistance.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mechanics")
public class MechanicEntity {

    @Id
    private UUID id;
   // private UUID userId;
   @Column(name = "user_id", nullable = false, unique = true)
   private UUID userId;
    private String fullName;
    private String speciality;
    private boolean available;

    //@Enumerated(EnumType.STRING)
    //@Column(nullable = false)
   // private MechanicAccountStatus status;

    private double rating;
    private double latitude;
    private double longitude;
   // private boolean approve =false;
   @Column(name = "approved", nullable = false)
   private boolean approved = false;



    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getSpeciality() { return speciality; }
    public void setSpeciality(String speciality) { this.speciality = speciality; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public boolean isApproved(){
        return this.approved;
    }
    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    }