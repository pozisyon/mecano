package com.mecano.assistance.infrastructure.websocket;

import com.mecano.assistance.domain.port.RealtimeNotificationPort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WebSocketRealtimeNotificationAdapter implements RealtimeNotificationPort {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketRealtimeNotificationAdapter(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void sendToUser(String username, String eventType, Object payload) {

    }

    @Override
    public void sendToDriver(String driverId, String eventType, Object payload) {
        messagingTemplate.convertAndSend(
                "/topic/drivers/" + driverId,
                new RealtimeEvent(eventType, payload)
        );
    }

    @Override
    public void sendToMechanic(String mechanicId, String eventType, Object payload) {
        messagingTemplate.convertAndSend(
                "/topic/mechanics/" + mechanicId,
                new RealtimeEvent(eventType, payload)
        );
    }
}