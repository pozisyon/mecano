package com.mecano.assistance.infrastructure.notification;

import com.mecano.assistance.domain.port.RealtimeNotificationPort;
import org.springframework.stereotype.Component;

@Component
public class FakeRealtimeNotificationAdapter implements RealtimeNotificationPort {

    @Override
    public void sendToUser(String username, String eventType, Object payload) {
        System.out.println("[REALTIME] " + eventType + " sent to " + username);
    }

    @Override
    public void sendToDriver(String driverId, String eventType, Object payload) {

    }

    @Override
    public void sendToMechanic(String mechanicId, String eventType, Object payload) {

    }
}