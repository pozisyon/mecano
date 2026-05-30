package com.mecano.assistance.domain.service;

import com.mecano.assistance.domain.model.BreakdownRequest;
import com.mecano.assistance.domain.model.Mechanic;
import com.mecano.assistance.domain.port.GeoDistancePort;

public class MechanicEligibilityService {

    private static final double MAX_DISTANCE_KM = 30.0;

    private final GeoDistancePort geoDistancePort;

    public MechanicEligibilityService(GeoDistancePort geoDistancePort) {
        this.geoDistancePort = geoDistancePort;
    }

    public boolean canAccept(Mechanic mechanic, BreakdownRequest request) {
        if (mechanic == null || request == null) {
            return false;
        }

        if (!mechanic.isAvailable()) {
            return false;
        }

        double distance = geoDistancePort
                .calculateDistance(request.getLocation(), mechanic.getCurrentLocation())
                .kilometers();

        return distance <= MAX_DISTANCE_KM;
    }
}