package com.mecano.assistance.domain.valueobject;

public record Distance(double kilometers) {

    public Distance {
        if (kilometers < 0) {
            throw new IllegalArgumentException("Distance cannot be negative");
        }
    }
}