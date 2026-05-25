package com.mecano.assistance.infrastructure.maps;

import com.mecano.assistance.domain.port.GeoDistancePort;
import com.mecano.assistance.domain.valueobject.Distance;
import com.mecano.assistance.domain.valueobject.Location;
import org.springframework.stereotype.Component;

@Component
public class HaversineGeoDistanceAdapter implements GeoDistancePort {

    private static final double EARTH_RADIUS_KM = 6371.0;

    @Override
    public Distance calculateDistance(Location from, Location to) {
        double latDistance = Math.toRadians(to.latitude() - from.latitude());
        double lonDistance = Math.toRadians(to.longitude() - from.longitude());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(from.latitude()))
                * Math.cos(Math.toRadians(to.latitude()))
                * Math.sin(lonDistance / 2)
                * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return new Distance(EARTH_RADIUS_KM * c);
    }
}