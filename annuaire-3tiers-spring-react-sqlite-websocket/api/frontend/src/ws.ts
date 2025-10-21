let ws: WebSocket | null = null;
const WS_URL = (location.origin.startsWith("http") ? location.origin.replace("http","ws") : "ws://localhost:8080") + "/ws";

export function connectWebSocket(onMessage: (msg: string) => void) {
  ws = new WebSocket(WS_URL);
  ws.onopen = () => console.log("✅ WS connecté: " + WS_URL);
  ws.onmessage = (event) => onMessage(event.data);
  ws.onclose = () => console.log("❌ WS fermé");
  ws.onerror = (e) => console.warn("⚠️ WS erreur", e);
}

export function sendMessage(msg: string) {
  if (ws && ws.readyState === WebSocket.OPEN) {
    ws.send(msg);
  } else {
    console.warn("⚠️ WebSocket non connecté");
  }
}
