import { useEffect, useState } from "react";
import { connectWebSocket, sendMessage } from "../ws";

export default function SocketDemo(){
  const [messages, setMessages] = useState<string[]>([]);
  const [input, setInput] = useState("");  

  useEffect(()=>{
    connectWebSocket((msg)=> setMessages(prev => [...prev, msg]));
  },[]);

  return (
    <div>
      <h1 style={{fontSize:24, fontWeight:700}}>🛰️ Démo WebSocket (natif)</h1>
      <div style={{border:"1px solid #ccc", padding:8, height:220, overflowY:"auto", background:"#fafafa"}}>
        {messages.map((m,i)=>(<div key={i}>👉 {m}</div>))}
      </div>
      <div style={{display:"flex", gap:8, marginTop:8}}>
        <input value={input} onChange={e=>setInput(e.target.value)} placeholder="Tape un message..."/>
        <button onClick={()=>{ sendMessage(input); setInput(""); }}>Envoyer</button>
      </div>
      <p style={{marginTop:12, color:"#666"}}>Ouvre deux onglets sur <code>/socket</code> pour voir le broadcast en temps réel.</p>
    </div>
  );
}
