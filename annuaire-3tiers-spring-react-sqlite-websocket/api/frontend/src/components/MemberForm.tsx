import React, { useState, useEffect } from "react";

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

interface MemberFormProps {
  selectedMember?: Member | null;
  onSave: (member: Member) => void;
  onCancel: () => void;
}

const MemberForm: React.FC<MemberFormProps> = ({ selectedMember, onSave, onCancel }) => {
  const [member, setMember] = useState<Member>(
    selectedMember || { nom: "", prenom: "", categorie: "", email: "" }
  );

  useEffect(() => {
    if (selectedMember) setMember(selectedMember);
  }, [selectedMember]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setMember((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSave(member);
  };

  return (
    <div className="bg-white shadow-md rounded-lg p-6 w-full md:w-1/2 mx-auto">
      <h2 className="text-xl font-bold mb-4 text-gray-700">
        {selectedMember ? "Modifier un membre" : "Ajouter un membre"}
      </h2>

      <form onSubmit={handleSubmit} className="space-y-4">
        <div className="grid grid-cols-2 gap-4">
          <input
            name="nom"
            placeholder="Nom"
            value={member.nom}
            onChange={handleChange}
            className="border p-2 rounded w-full"
            required
          />
          <input
            name="prenom"
            placeholder="Prénom"
            value={member.prenom}
            onChange={handleChange}
            className="border p-2 rounded w-full"
            required
          />
        </div>

        <div className="grid grid-cols-2 gap-4">
          <select
            name="categorie"
            value={member.categorie}
            onChange={handleChange}
            className="border p-2 rounded w-full"
            required
          >
            <option value="">Catégorie</option>
            <option value="PROF">Professeur</option>
            <option value="AUX">Auxiliaire</option>
            <option value="ETUDIANT">Étudiant</option>
          </select>

          <input
            name="email"
            placeholder="Email"
            type="email"
            value={member.email}
            onChange={handleChange}
            className="border p-2 rounded w-full"
            required
          />
        </div>

        <div className="grid grid-cols-2 gap-4">
          <input
            name="telephone"
            placeholder="Téléphone"
            value={member.telephone || ""}
            onChange={handleChange}
            className="border p-2 rounded w-full"
          />
          <input
            name="domaine"
            placeholder="Domaine"
            value={member.domaine || ""}
            onChange={handleChange}
            className="border p-2 rounded w-full"
          />
        </div>

        <div className="flex justify-end space-x-3 mt-6">
          <button
            type="button"
            onClick={onCancel}
            className="px-4 py-2 bg-gray-300 hover:bg-gray-400 text-black rounded"
          >
            Annuler
          </button>
          <button
            type="submit"
            className="px-4 py-2 bg-blue-600 hover:bg-blue-700 text-white rounded"
          >
            Enregistrer
          </button>
        </div>
      </form>
    </div>
  );
};

export default MemberForm;
