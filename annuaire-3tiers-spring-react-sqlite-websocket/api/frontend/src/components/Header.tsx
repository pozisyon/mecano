import React from "react";
import { useNavigate } from "react-router-dom";

export default function Header() {
  const navigate = useNavigate();
  const isAuthenticated = localStorage.getItem("authToken") !== null;

  const handleLogout = () => {
    localStorage.removeItem("authToken");
    navigate("/");
  };

  const handleLogin = () => {
    // Pour les tests on simule un login admin (plus tard via API Spring Security)
    localStorage.setItem("authToken", "mock-token");
    navigate("/admin");
  };

  return (
    <header className="bg-blue-800 text-white p-4 flex justify-between items-center shadow">
      <h1
        className="text-xl font-bold cursor-pointer"
        onClick={() => navigate("/")}
      >
        Annuaire UQTR
      </h1>
      <nav className="flex gap-6">
        <button onClick={() => navigate("/public")}>Espace public</button>
        {isAuthenticated ? (
          <button
            onClick={handleLogout}
            className="bg-red-600 px-4 py-1 rounded hover:bg-red-700"
          >
            Déconnexion
          </button>
        ) : (
          <button
            onClick={handleLogin}
            className="bg-green-600 px-4 py-1 rounded hover:bg-green-700"
          >
            Connexion admin
          </button>
        )}
      </nav>
    </header>
  );
}
