import React from "react";
import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div className="min-h-screen flex flex-col bg-gray-50 text-gray-800">
      {/* HEADER */}
      <header className="bg-blue-900 text-white py-6 shadow-lg">
        <div className="max-w-6xl mx-auto px-4 text-center">
          <h1 className="text-3xl md:text-4xl font-bold tracking-wide">
            Annuaire Réseau
          </h1>
          <p className="text-blue-200 mt-2 text-lg italic">
            Devoir académique - Architecture client/serveur avec communication par Socket
          </p>
        </div>
      </header>

      {/* MAIN CONTENT */}
      <main className="flex-1 max-w-5xl mx-auto px-6 py-12">
        {/* CONTEXTE */}
        <section className="bg-white rounded-2xl shadow-xl p-8 mb-10 border border-gray-200">
          <h2 className="text-2xl font-semibold mb-4 text-blue-800">
            📘 Contexte du projet
          </h2>
          <p className="text-justify leading-relaxed text-gray-700">
            Ce projet s’inscrit dans le cadre du cours de <strong>réseaux informatiques</strong> et vise à
            mettre en œuvre une application <strong>3-tiers</strong> (client – serveur – base de données)
            permettant la gestion d’un <strong>annuaire de membres</strong> universitaire.
          </p>
          <p className="text-justify leading-relaxed mt-4 text-gray-700">
            L’objectif principal est de concevoir un système dans lequel :
          </p>
          <ul className="list-disc list-inside mt-2 ml-4 space-y-2 text-gray-700">
            <li>les <strong>clients</strong> peuvent consulter les membres selon des critères précis (catégorie, domaine, etc.);</li>
            <li>un <strong>administrateur</strong> authentifié peut gérer l’annuaire (ajouter, modifier, supprimer, lister ou marquer un membre en liste rouge);</li>
            <li>les échanges entre clients et serveur sont gérés via une <strong>communication WebSocket</strong> pour permettre un mode connecté et synchrone.</li>
          </ul>
        </section>

        {/* CARDS */}
        <section className="grid grid-cols-1 md:grid-cols-2 gap-10">
          {/* Espace Public */}
          <div className="bg-gradient-to-br from-blue-50 to-blue-100 rounded-2xl shadow-md p-8 border border-blue-200 hover:shadow-xl transition">
            <h3 className="text-xl font-semibold text-blue-800 mb-3">🌍 Espace Public</h3>
            <p className="text-gray-700 mb-6 text-justify">
              Consultez les informations publiques des membres selon la catégorie ou le domaine.
              Les membres en liste rouge ne sont visibles que par leur nom et prénom.
            </p>
            <Link
              to="/public"
              className="inline-block bg-blue-700 text-white px-5 py-2 rounded-lg font-medium hover:bg-blue-800"
            >
              Accéder à l’espace public
            </Link>
          </div>

          {/* Espace Admin */}
          <div className="bg-gradient-to-br from-gray-50 to-gray-200 rounded-2xl shadow-md p-8 border border-gray-300 hover:shadow-xl transition">
            <h3 className="text-xl font-semibold text-gray-800 mb-3">🧑‍💼 Espace Administrateur</h3>
            <p className="text-gray-700 mb-6 text-justify">
              Accédez aux fonctions de gestion de l’annuaire : ajout, modification, suppression,
              et gestion de la liste rouge. Cette section nécessite une authentification.
            </p>
            <Link
              to="/admin"
              className="inline-block bg-gray-800 text-white px-5 py-2 rounded-lg font-medium hover:bg-gray-900"
            >
              Accéder à l’espace admin
            </Link>
          </div>
        </section>
      </main>

      {/* FOOTER */}
      <footer className="bg-blue-900 text-blue-100 py-6 text-center mt-10">
        <p className="text-sm">
          Université du Québec à Trois-Rivières — Département d’Informatique<br />
          Devoir de : <strong>Réseaux</strong> | Automne 2025
        </p>
      </footer>
    </div>
  );
}
