import React, { useEffect, useState } from "react";

interface Member {
  id?: number;
  nom: string;
  prenom: string;
  categorie: string;
  email: string;
  telephone?: string;
  domaine?: string;
  red?: boolean;
}

export default function AdminPage() {
  const [members, setMembers] = useState<Member[]>([]);
  const [form, setForm] = useState<Member>({
    nom: "",
    prenom: "",
    categorie: "ETUDIANT",
    email: "",
    telephone: "",
    domaine: "",
    red: false,
  });
  const [editing, setEditing] = useState<Member | null>(null);
  const [message, setMessage] = useState<string>("");

  // Charger tous les membres
  const loadMembers = () => {
    fetch("/api/admin/members")
      .then((res) => res.json())
      .then(setMembers)
      .catch((err) => console.error("Erreur chargement membres:", err));
  };

  useEffect(() => {
    loadMembers();
  }, []);

  // Ajouter un membre
  const handleAdd = (e: React.FormEvent) => {
    e.preventDefault();
    fetch("/api/admin/members", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(form),
    })
      .then((res) => res.json())
      .then((data) => {
        setMessage("✅ Membre ajouté !");
        setMembers([...members, data]);
        setForm({
          nom: "",
          prenom: "",
          categorie: "ETUDIANT",
          email: "",
          telephone: "",
          domaine: "",
          red: false,
        });
      })
      .catch(() => setMessage("❌ Erreur d’ajout"));
  };

  // Supprimer un membre
  const handleDelete = (id?: number) => {
    if (!id) return;
    if (!confirm("Voulez-vous vraiment supprimer ce membre ?")) return;
    fetch(`/api/admin/members/${id}`, { method: "DELETE" })
      .then(() => {
        setMembers(members.filter((m) => m.id !== id));
        setMessage("🗑️ Membre supprimé");
      })
      .catch(() => setMessage("❌ Erreur de suppression"));
  };

  // Modifier un membre
  const handleEdit = (m: Member) => {
    setEditing(m);
  };

  const handleUpdate = (e: React.FormEvent) => {
    e.preventDefault();
    if (!editing?.id) return;
    fetch(`/api/admin/members/${editing.id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(editing),
    })
      .then((res) => res.json())
      .then((updated) => {
        setMembers(
          members.map((m) => (m.id === updated.id ? updated : m))
        );
        setEditing(null);
        setMessage("✅ Membre mis à jour !");
      })
      .catch(() => setMessage("❌ Erreur de mise à jour"));
  };

  // Basculer liste rouge
  const toggleRed = (id?: number) => {
    const m = members.find((x) => x.id === id);
    if (!m) return;
    fetch(`/api/admin/members/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ ...m, red: !m.red }),
    })
      .then((res) => res.json())
      .then((updated) => {
        setMembers(members.map((x) => (x.id === id ? updated : x)));
      });
  };

  return (
    <div className="min-h-screen bg-gray-100 p-10">
      <h1 className="text-3xl font-bold text-center mb-8 text-blue-800">
        🧭 Tableau de bord – Administration
      </h1>

      {message && (
        <div className="bg-green-100 text-green-700 px-4 py-2 rounded mb-4">
          {message}
        </div>
      )}

      {/* Formulaire d'ajout */}
      <form
        onSubmit={handleAdd}
        className="bg-white rounded-lg shadow p-6 mb-10"
      >
        <h2 className="text-xl font-semibold mb-4 text-gray-700">
          ➕ Ajouter un membre
        </h2>
        <div className="grid md:grid-cols-2 gap-4">
          <input
            className="border p-2 rounded"
            placeholder="Nom"
            value={form.nom}
            onChange={(e) => setForm({ ...form, nom: e.target.value })}
            required
          />
          <input
            className="border p-2 rounded"
            placeholder="Prénom"
            value={form.prenom}
            onChange={(e) => setForm({ ...form, prenom: e.target.value })}
            required
          />
          <select
            className="border p-2 rounded"
            value={form.categorie}
            onChange={(e) => setForm({ ...form, categorie: e.target.value })}
          >
            <option value="PROF">Professeur</option>
            <option value="AUX">Auxiliaire</option>
            <option value="ETUDIANT">Étudiant</option>
          </select>
          <input
            className="border p-2 rounded"
            placeholder="Email"
            value={form.email}
            onChange={(e) => setForm({ ...form, email: e.target.value })}
            required
          />
          <input
            className="border p-2 rounded"
            placeholder="Téléphone"
            value={form.telephone}
            onChange={(e) => setForm({ ...form, telephone: e.target.value })}
          />
          <input
            className="border p-2 rounded"
            placeholder="Domaine"
            value={form.domaine}
            onChange={(e) => setForm({ ...form, domaine: e.target.value })}
          />
        </div>
        <button
          type="submit"
          className="mt-5 bg-blue-700 text-white px-6 py-2 rounded hover:bg-blue-800"
        >
          Ajouter
        </button>
      </form>

      {/* Liste des membres */}
      <div className="bg-white rounded-lg shadow p-6">
        <h2 className="text-xl font-semibold mb-4 text-gray-700">
          📋 Liste des membres
        </h2>

        <table className="min-w-full border text-sm text-left">
          <thead className="bg-gray-100">
            <tr>
              <th className="p-2">Nom</th>
              <th className="p-2">Prénom</th>
              <th className="p-2">Catégorie</th>
              <th className="p-2">Email</th>
              <th className="p-2">Domaine</th>
              <th className="p-2 text-center">Rouge</th>
              <th className="p-2 text-center">Actions</th>
            </tr>
          </thead>
          <tbody>
            {members.map((m) => (
              <tr
                key={m.id}
                className={`border-t ${
                  m.red ? "bg-red-100 text-red-800" : "hover:bg-gray-50"
                }`}
              >
                <td className="p-2">{m.nom}</td>
                <td className="p-2">{m.prenom}</td>
                <td className="p-2">{m.categorie}</td>
                <td className="p-2">{m.email}</td>
                <td className="p-2">{m.domaine}</td>
                <td className="p-2 text-center">
                  <input
                    type="checkbox"
                    checked={m.red}
                    onChange={() => toggleRed(m.id)}
                  />
                </td>
                <td className="p-2 flex justify-center gap-2">
                  <button
                    onClick={() => handleEdit(m)}
                    className="px-3 py-1 bg-yellow-400 rounded hover:bg-yellow-500"
                  >
                    ✏️
                  </button>
                  <button
                    onClick={() => handleDelete(m.id)}
                    className="px-3 py-1 bg-red-500 text-white rounded hover:bg-red-600"
                  >
                    🗑️
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Modale d’édition */}
      {editing && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <form
            onSubmit={handleUpdate}
            className="bg-white p-6 rounded-lg shadow-xl w-96"
          >
            <h3 className="text-lg font-semibold mb-3">Modifier le membre</h3>
            <input
              className="border p-2 rounded w-full mb-2"
              placeholder="Nom"
              value={editing.nom}
              onChange={(e) =>
                setEditing({ ...editing, nom: e.target.value })
              }
            />
            <input
              className="border p-2 rounded w-full mb-2"
              placeholder="Prénom"
              value={editing.prenom}
              onChange={(e) =>
                setEditing({ ...editing, prenom: e.target.value })
              }
            />
            <input
              className="border p-2 rounded w-full mb-2"
              placeholder="Email"
              value={editing.email}
              onChange={(e) =>
                setEditing({ ...editing, email: e.target.value })
              }
            />
            <input
              className="border p-2 rounded w-full mb-2"
              placeholder="Domaine"
              value={editing.domaine}
              onChange={(e) =>
                setEditing({ ...editing, domaine: e.target.value })
              }
            />
            <div className="flex justify-end gap-2">
              <button
                type="button"
                onClick={() => setEditing(null)}
                className="bg-gray-300 px-4 py-2 rounded"
              >
                Annuler
              </button>
              <button
                type="submit"
                className="bg-green-600 text-white px-4 py-2 rounded"
              >
                Enregistrer
              </button>
            </div>
          </form>
        </div>
      )}
    </div>
  );
}
