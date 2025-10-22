package com.annuaire.api.service;

import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.context.event.EventListener;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ClientTracker {
    private final SimpMessagingTemplate template;
    private final Map<String, String> clients = new ConcurrentHashMap<>();

    public ClientTracker(SimpMessagingTemplate template) {
        this.template = template;
    }

    @EventListener
    public void handleConnect(SessionConnectedEvent event) {
        var sessionId = Objects.requireNonNull(event.getMessage().getHeaders().get("simpSessionId")).toString();
        var user = "Client-" + sessionId.substring(0, 5);
        clients.put(sessionId, user);
        broadcast();
    }

    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event) {
        var sessionId = event.getSessionId();
        clients.remove(sessionId);
        broadcast();
    }

    private void broadcast() {
        template.convertAndSend("/topic/clients", clients.values());
    }

    public Collection<String> getClients() {
        return clients.values();
    }
}
