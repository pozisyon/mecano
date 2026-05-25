package com.mecano.assistance.domain.port;

import com.mecano.assistance.domain.valueobject.Distance;
import com.mecano.assistance.domain.valueobject.Location;

public interface GeoDistancePort {

    Distance calculateDistance(Location from, Location to);
}