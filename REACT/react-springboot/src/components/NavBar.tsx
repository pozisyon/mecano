import { NavLink } from "react-router-dom";

export default function NavBar() {
  return (
    <nav className="nav">
      <div className="container navRow">
        <NavLink className="navLink" to="/">Accueil</NavLink>
        <NavLink className="navLink" to="/entities">Entités</NavLink>
        <NavLink className="navLink" to="/entities/new">Ajouter</NavLink>
      </div>
    </nav>
  );
}
