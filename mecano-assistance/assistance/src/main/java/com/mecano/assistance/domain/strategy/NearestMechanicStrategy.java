package com.mecano.assistance.domain.strategy;

import com.mecano.assistance.domain.model.BreakdownRequest;
import com.mecano.assistance.domain.model.Mechanic;
import com.mecano.assistance.domain.port.GeoDistancePort;

import java.util.Comparator;
import java.util.List;

public class NearestMechanicStrategy implements MatchingStrategy {

    private final GeoDistancePort geoDistancePort;

    public NearestMechanicStrategy(GeoDistancePort geoDistancePort) {
        this.geoDistancePort = geoDistancePort;
    }

    @Override
    public List<Mechanic> match(BreakdownRequest request, List<Mechanic> mechanics) {
        return mechanics.stream()
                .filter(Mechanic::isAvailable)
                .sorted(Comparator.comparingDouble(mechanic ->
                        geoDistancePort.calculateDistance(
                                request.getLocation(),
                                mechanic.getCurrentLocation()
                        ).kilometers()
                ))
                .limit(10)
                .toList();
    }
}