import { useState } from "react";
import { api, setAdminAuth } from "../api";

export default function Admin(){
  const [user, setUser] = useState("admin");
  const [pass, setPass] = useState("admin123");
  const [members, setMembers] = useState<any[]>([]);

  const login = async () => { setAdminAuth(user, pass); await loadAll(); };
  const loadAll = async () => {
    const { data } = await api.get(`/api/admin/members`);
    setMembers(data);
  };
  const toggleRed = async (m:any) => {
    await api.put(`/api/admin/members/${m.id}/red/${!m.red}`);
    loadAll();
  };
  const del = async (m:any) => {
    await api.delete(`/api/admin/members/${m.id}`);
    loadAll();
  };

  return (
    <div>
      <h1 style={{fontSize:24, fontWeight:700}}>Admin</h1>
      <div style={{display:"flex", gap:8}}>
        <input value={user} onChange={e=>setUser(e.target.value)} placeholder="admin user"/>
        <input value={pass} onChange={e=>setPass(e.target.value)} type="password" placeholder="admin pass"/>
        <button onClick={login}>Se connecter</button>
      </div>
      <button style={{marginTop:8}} onClick={loadAll}>Rafraîchir</button>
      <table style={{marginTop:16, width:"100%", borderCollapse:"collapse"}}>
        <thead><tr><th>ID</th><th>Nom</th><th>Prénom</th><th>Cat.</th><th>Email</th><th>Rouge</th><th>Actions</th></tr></thead>
        <tbody>
          {members.map((m:any)=>(
            <tr key={m.id}>
              <td>{m.id}</td><td>{m.nom}</td><td>{m.prenom}</td><td>{m.categorie}</td>
              <td>{m.email}</td><td>{m.red ? "Oui":"Non"}</td>
              <td style={{display:"flex", gap:8}}>
                <button onClick={()=>toggleRed(m)}>{m.red ? "Enlever rouge":"Mettre rouge"}</button>
                <button onClick={()=>del(m)}>Supprimer</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
