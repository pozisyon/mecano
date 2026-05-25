package com.mecano.assistance.domain.valueobject;

public record Location(double latitude, double longitude) {

    public Location{
         if(latitude <- 90 || latitude > 90) {throw new IllegalArgumentException("Incorrect Latitude");}
         if(longitude < -180 || longitude > 180) {throw new IllegalArgumentException("Incorrct Longitude ");}
    }
}
