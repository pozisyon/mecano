import React, { useCallback, useEffect, useMemo, useState } from "react";
import { useSocket } from "../hooks/useSocket";
import MemberForm from "../components/MemberForm";
import {
  adminListAll,
  adminAdd,
  adminUpdate,
  adminDelete,
  adminToggleRed,
} from "../api/client";
import MemberForm from "../components/MemberForm";
import type { Member } from "../types";

function toBasic(user: string, pass: string) {
  return "Basic " + btoa(`${user}:${pass}`);
}

const AdminPage: React.FC = () => {
  const [auth, setAuth] = useState<string | undefined>();
  const [user, setUser] = useState("admin");
  const [pass, setPass] = useState("admin123");

  const [members, setMembers] = useState<Member[]>([]);
  const [editing, setEditing] = useState<Member | null>(null);
  const [clients, setClients] = useState<string[]>([]);

  const opts = useMemo(() => ({ auth }), [auth]);

  const loadMembers = useCallback(async () => {
    try {
      const data = await adminListAll(opts);
      setMembers(data);
    } catch (e) {
      console.error("Erreur de chargement des membres:", e);
    }
  }, [opts]);

  // --- WebSocket : actualisation automatique ---
  useSocket(loadMembers, setClients);

  useEffect(() => {
    if (auth) loadMembers();
  }, [auth, loadMembers]);

  async function handleLogin() {
    const a = toBasic(user, pass);
    setAuth(a);
  }

  async function handleAdd(m: Member | Partial<Member>) {
    try {
      await adminAdd(m as Member, opts);
      setEditing(null);
      loadMembers();
    } catch (e) {
      alert("Erreur lors de l’ajout");
    }
  }

  async function handleUpdate(m: Member | Partial<Member>) {
    if (!editing?.id) return;
    try {
      await adminUpdate(editing.id, m, opts);
      setEditing(null);
      loadMembers();
    } catch (e) {
      alert("Erreur lors de la mise à jour");
    }
  }

  async function handleDelete(id: number) {
    if (!confirm("Supprimer ce membre ?")) return;
    try {
      await adminDelete(id, opts);
      loadMembers();
    } catch {
      alert("Erreur lors de la suppression");
    }
  }

  async function handleToggleRed(m: Member) {
    try {
      await adminToggleRed(m.id!, !m.red, opts);
      loadMembers();
    } catch {
      alert("Erreur lors du changement de statut rouge");
    }
  }

  return (
    <div className="min-h-screen bg-gray-50">
      {/* HEADER */}
      <header className="bg-white shadow p-4 flex flex-col md:flex-row md:items-center md:justify-between gap-3">
        <h1 className="text-2xl font-bold text-blue-700">Espace Administrateur</h1>

        {!auth ? (
          <div className="flex gap-2">
            <input
              className="input"
              placeholder="Nom d’utilisateur"
              value={user}
              onChange={(e) => setUser(e.target.value)}
            />
            <input
              className="input"
              type="password"
              placeholder="Mot de passe"
              value={pass}
              onChange={(e) => setPass(e.target.value)}
            />
            <button className="btn-primary" onClick={handleLogin}>
              Se connecter
            </button>
          </div>
        ) : (
          <div className="flex items-center gap-3">
            <span className="text-gray-600">Connecté : {user}</span>
            <button
              className="btn"
              onClick={() => {
                setAuth(undefined);
                setMembers([]);
              }}
            >
              Déconnexion
            </button>
          </div>
        )}
      </header>

      <main className="max-w-6xl mx-auto p-6 space-y-10">
        {/* FORMULAIRE ADD / EDIT */}
        <section className="card">
          <div className="flex items-center justify-between mb-4">
            <h2 className="text-xl font-semibold text-gray-800">
              {editing ? "Modifier un membre" : "Ajouter un membre"}
            </h2>
            {editing && (
              <button className="btn" onClick={() => setEditing(null)}>
                Annuler
              </button>
            )}
          </div>

          <MemberForm
            initial={editing ?? {}}
            onSubmit={editing ? handleUpdate : handleAdd}
            submitLabel={editing ? "Mettre à jour" : "Ajouter"}
          />
        </section>

        {/* TABLEAU DES MEMBRES */}
        <section className="card">
          <div className="flex items-center justify-between mb-4">
            <h2 className="text-xl font-semibold text-gray-800">Membres enregistrés</h2>
            <button className="btn" onClick={loadMembers}>
              Rafraîchir
            </button>
          </div>

          <div className="overflow-auto">
            <table className="w-full border-collapse">
              <thead>
                <tr className="bg-gray-100 text-left">
                  <th className="p-3">Nom</th>
                  <th className="p-3">Prénom</th>
                  <th className="p-3">Catégorie</th>
                  <th className="p-3">Email</th>
                  <th className="p-3">Domaine</th>
                  <th className="p-3">Statut</th>
                  <th className="p-3">Actions</th>
                </tr>
              </thead>
              <tbody>
                {members.map((m) => (
                  <tr
                    key={m.id}
                    className="border-b hover:bg-gray-50 transition-all"
                  >
                    <td className="p-3">{m.nom}</td>
                    <td className="p-3">{m.prenom}</td>
                    <td className="p-3">{m.categorie}</td>
                    <td className="p-3">{m.email}</td>
                    <td className="p-3">{m.domaine || "—"}</td>
                    <td className="p-3">
                      <span
                        className={`px-2 py-1 rounded text-sm font-semibold ${
                          m.red
                            ? "bg-red-100 text-red-700"
                            : "bg-green-100 text-green-700"
                        }`}
                      >
                        {m.red ? "Sur liste rouge" : "OK"}
                      </span>
                    </td>
                    <td className="p-3 flex flex-wrap gap-2">
                      <button
                        className="btn text-blue-600 border-blue-500"
                        onClick={() => setEditing(m)}
                      >
                        Modifier
                      </button>
                      <button
                        className="btn text-yellow-700 border-yellow-600"
                        onClick={() => handleToggleRed(m)}
                      >
                        {m.red ? "Enlever rouge" : "Mettre rouge"}
                      </button>
                      <button
                        className="btn text-red-700 border-red-600"
                        onClick={() => handleDelete(m.id!)}
                      >
                        Supprimer
                      </button>
                    </td>
                  </tr>
                ))}
                {!members.length && (
                  <tr>
                    <td className="p-3 text-gray-500" colSpan={7}>
                      Aucun membre trouvé
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </section>

        {/* CLIENTS CONNECTÉS */}
        <section className="card">
          <h2 className="text-xl font-semibold mb-3 text-gray-800">
            Clients connectés (via WebSocket)
          </h2>
          <ul className="list-disc ml-6">
            {clients.map((c) => (
              <li key={c}>{c}</li>
            ))}
            {!clients.length && (
              <li className="text-gray-500">Aucun client connecté</li>
            )}
          </ul>
        </section>
      </main>
    </div>
  );
};

export default AdminPage;
