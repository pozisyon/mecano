package com.mecano.assistance.infrastructure.websocket;

import java.time.LocalDateTime;

public record RealtimeEvent(
        String type,
        Object payload,
        LocalDateTime timestamp
) {
    public RealtimeEvent(String type, Object payload) {
        this(type, payload, LocalDateTime.now());
    }
}