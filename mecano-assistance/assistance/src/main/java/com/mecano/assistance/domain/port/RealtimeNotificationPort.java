package com.mecano.assistance.domain.port;

public interface RealtimeNotificationPort {

    void sendToUser(String username, String eventType, Object payload);
        void sendToDriver(String driverId, String eventType, Object payload);
        void sendToMechanic(String mechanicId, String eventType, Object payload);

}