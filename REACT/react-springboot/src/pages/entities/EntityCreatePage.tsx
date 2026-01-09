import { useNavigate } from "react-router-dom";
import EntityForm from "../../components/EntityForm";
import type { Entity } from "../../models/entity";
import { createEntity } from "../../api/entityService";

export default function EntityCreatePage() {
  const navigate = useNavigate();

const initial: Entity = { nom: "", prenom: "", email: "" };



  return (
    <div className="card">
      <h2>Ajouter</h2>
      <EntityForm
        initialValue={initial}
        submitLabel="Enregistrer"
        onSubmit={async (entity) => {
          await createEntity(entity);
          navigate("/entities");
        }}
      />
    </div>
  );
}
