import { useEffect, useRef } from "react";

export function useSocket(onMessage: (msg: any) => void) {
  const socketRef = useRef<WebSocket | null>(null);

  useEffect(() => {
    // Connexion au WebSocket Spring Boot
    const socket = new WebSocket("ws://localhost:8080/ws");
    socketRef.current = socket;

    socket.onopen = () => console.log("✅ WebSocket connecté");
    socket.onmessage = (event) => {
      try {
        const data = JSON.parse(event.data);
        onMessage(data);
      } catch (e) {
        console.error("Erreur message WS:", e);
      }
    };
    socket.onclose = () => console.log("❌ WebSocket fermé");
    socket.onerror = (err) => console.error("⚠️ WebSocket error:", err);

    return () => socket.close();
  }, [onMessage]);

  return socketRef.current;
}
