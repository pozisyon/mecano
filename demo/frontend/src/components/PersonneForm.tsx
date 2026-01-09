mport { useState } from "react";
import { Personne } from "../models/Personne";
import { createPersonne } from "../api/personneService";

const PersonneForm = () => {
  const [personne, setPersonne] = useState<Personne>({
    nom: "",
    prenom: "",
    email: ""
  });

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    const { name, value } = e.target;

    setPersonne({
      ...personne,
      [name]: value
    });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    createPersonne(personne).then(() => {
      alert("Personne ajoutée");
      setPersonne({ nom: "", prenom: "", email: "" });
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Ajouter une personne</h2>

      <input name="nom" value={personne.nom} onChange={handleChange} placeholder="Nom" />
      <input name="prenom" value={personne.prenom} onChange={handleChange} placeholder="Prénom" />
      <input name="email" value={personne.email} onChange={handleChange} placeholder="Email" />

      <button type="submit">Enregistrer</button>
    </form>
  );
};

export default PersonneForm;