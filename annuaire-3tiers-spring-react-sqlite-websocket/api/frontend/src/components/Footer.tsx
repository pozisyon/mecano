import React from "react";

export default function Footer() {
  return (
    <footer className="mt-12 bg-gray-900 text-gray-200 py-6">
      <div className="max-w-6xl mx-auto px-4 flex flex-col md:flex-row justify-between items-center">
        <p className="text-sm text-center md:text-left">
          © {new Date().getFullYear()} Département d’Informatique — UQTR
        </p>
        <p className="text-sm mt-2 md:mt-0 text-center md:text-right">
          Projet académique — Annuaire des Membres |
          <span className="text-blue-400 ml-1">Réseaux & WebSocket</span>
        </p>
      </div>
    </footer>
  );
}
