package com.annuaire.api.socket;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/ws")
@Component
public class AnnuaireSocket {

    private static final Set<Session> sessions = ConcurrentHashMap.newKeySet();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        broadcast("🟢 Connexion: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        broadcast("[" + session.getId() + "] " + message);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
        broadcast("🔴 Déconnexion: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        broadcast("⚠️ Erreur: " + throwable.getMessage());
    }

    private void broadcast(String msg){
        for (Session s : sessions) {
            if (s.isOpen()) {
                s.getAsyncRemote().sendText(msg);
            }
        }
    }
}
