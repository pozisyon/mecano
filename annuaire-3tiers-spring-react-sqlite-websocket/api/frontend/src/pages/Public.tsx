import React, { useEffect, useState } from "react";
import { useSocket } from "../hooks/useSocket";
import { getAllMembers, searchMembers } from "../api/client";
import type { Member } from "../types";

const PublicPage: React.FC = () => {
  const [members, setMembers] = useState<Member[]>([]);
  const [categorie, setCategorie] = useState("");
  const [domaine, setDomaine] = useState("");
  const [query, setQuery] = useState("");
  const [loading, setLoading] = useState(false);

  // 🔁 Recharge la liste lors d'un événement WebSocket
  useSocket(() => loadMembers());

  // --- Charger les membres ---
  async function loadMembers() {
    setLoading(true);
    try {
      const res = await getAllMembers();
      setMembers(res);
    } catch (e) {
      console.error("Erreur de chargement :", e);
    }
    setLoading(false);
  }

  // --- Recherche spécifique ---
  async function handleSearch() {
    setLoading(true);
    try {
      const res = await searchMembers({ categorie, domaine, query });
      setMembers(res);
    } catch (e) {
      console.error("Erreur recherche :", e);
    }
    setLoading(false);
  }

  useEffect(() => {
    loadMembers();
  }, []);

  return (
    <div className="min-h-screen bg-gray-50">
      {/* HEADER */}
      <header className="bg-blue-600 text-white py-5 px-8 shadow-lg">
        <h1 className="text-3xl font-bold">Annuaire des Membres UQTR</h1>
        <p className="text-sm opacity-80">
          Recherche et consultation des membres du campus
        </p>
      </header>

      {/* CONTENU */}
      <main className="max-w-6xl mx-auto p-8 space-y-10">
        {/* 🔍 BARRE DE RECHERCHE */}
        <section className="card space-y-6">
          <h2 className="text-xl font-semibold text-gray-800">
            Rechercher un membre
          </h2>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            <input
              className="input"
              placeholder="Nom, prénom, email, matricule..."
              value={query}
              onChange={(e) => setQuery(e.target.value)}
            />
            <select
              className="input"
              value={categorie}
              onChange={(e) => setCategorie(e.target.value)}
            >
              <option value="">-- Catégorie --</option>
              <option value="PROF">Professeur</option>
              <option value="AUX">Auxiliaire</option>
              <option value="ETUDIANT">Étudiant</option>
            </select>
            <input
              className="input"
              placeholder="Domaine (si PROF)"
              value={domaine}
              onChange={(e) => setDomaine(e.target.value)}
            />
          </div>

          <div className="flex gap-4">
            <button className="btn-primary" onClick={handleSearch}>
              Rechercher
            </button>
            <button className="btn" onClick={loadMembers}>
              Réinitialiser
            </button>
          </div>
        </section>

        {/* 🧑‍🎓 LISTE DES MEMBRES */}
        <section className="card">
          <h2 className="text-xl font-semibold mb-4 text-gray-800">
            Résultats ({members.length})
          </h2>

          {loading ? (
            <p className="text-gray-500">Chargement...</p>
          ) : (
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {members.map((m) => (
                <div
                  key={m.id}
                  className="p-4 border rounded-xl shadow-sm bg-white hover:shadow-md transition-all"
                >
                  {m.red ? (
                    <>
                      <h3 className="font-semibold text-lg text-red-600">
                        {m.nom} {m.prenom}
                      </h3>
                      <p className="text-sm text-red-500 mt-2">
                        ⚠️ Membre sur la liste rouge
                      </p>
                    </>
                  ) : (
                    <>
                      <h3 className="font-semibold text-lg text-blue-700">
                        {m.nom} {m.prenom}
                      </h3>
                      <p className="text-sm text-gray-700">{m.categorie}</p>
                      {m.domaine && (
                        <p className="text-sm text-gray-500">
                          Domaine : {m.domaine}
                        </p>
                      )}
                      {m.matricule && (
                        <p className="text-sm text-gray-500">
                          Matricule : {m.matricule}
                        </p>
                      )}
                      <p className="text-sm text-gray-500">{m.email}</p>
                      {m.telephone && (
                        <p className="text-sm text-gray-500">{m.telephone}</p>
                      )}
                    </>
                  )}
                </div>
              ))}
              {!members.length && (
                <p className="text-gray-500 text-center col-span-full">
                  Aucun membre trouvé
                </p>
              )}
            </div>
          )}
        </section>
      </main>
    </div>
  );
};

export default PublicPage;
