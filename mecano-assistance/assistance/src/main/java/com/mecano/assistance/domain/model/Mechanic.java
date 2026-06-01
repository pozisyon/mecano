package com.mecano.assistance.domain.model;

import com.mecano.assistance.domain.valueobject.Location;

import java.util.UUID;

public class Mechanic {

    private final UUID id;
    private final String fullName;
    private final String speciality;
    private  boolean available;
    private final double rating;
    private final Location currentLocation;
    private  boolean approved ;//= false;
    //private final boolean available = false;
  //  private MechanicAccountStatus status;
    public Mechanic(
            UUID id,
            String fullName,
            String speciality,
            boolean available,
            boolean approved,
          //  MechanicAccountStatus status,
            double rating,
            Location currentLocation

           // boolean available
    ) {
        if (id == null) throw new IllegalArgumentException("Mechanic id is required");
        if (currentLocation == null) throw new IllegalArgumentException("Mechanic location is required");

        this.id = id;
        this.fullName = fullName;
        this.speciality = speciality;
        this.available = available;
      //  this.status = status;
        this.rating = rating;
        this.currentLocation = currentLocation;
        this.approved = approved;
        //this.available = true;
    }

    public UUID getId() { return id; }
    public String getFullName() { return fullName; }
    public String getSpeciality() { return speciality; }
    public boolean isAvailable() { return available; }
    public double getRating() { return rating; }
    public Location getCurrentLocation() { return currentLocation; }

    public boolean isApproved() {
        return this.approved;
    }


    /*public void setAvailable(boolean available){
        this.available = available;
    }
    public void setApproved(boolean approved){
        this.approved = approved;
    }
*/
    public void approve() {
        this.approved = true;
    }




    public void reject() {
        this.approved = false;
        this.available = false;
    }


    public void suspend() {
       // this.status = MechanicAccountStatus.SUSPENDED;
        this.available = false;
    }

    /*public MechanicAccountStatus getStatus() {
        return status;
    }*/

}