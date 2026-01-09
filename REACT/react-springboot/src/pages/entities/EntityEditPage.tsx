import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import EntityForm from "../../components/EntityForm";
import type { Entity } from "../../models/entity";
import { getEntityById, updateEntity } from "../../api/entityService";
import { ErrorBox, Loading } from "../../components/Ui";

export default function EntityEditPage() {
  const { id } = useParams();
  const navigate = useNavigate();
  const entityId = Number(id);

  const [initial, setInitial] = useState<Entity | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    setError(null);
    getEntityById(entityId)
      .then((res) => setInitial(res.data))
      .catch(() => setError("Impossible de charger l’élément."));
  }, [entityId]);

  if (error) return <div className="card"><ErrorBox message={error} /></div>;
  if (!initial) return <div className="card"><Loading /></div>;

  return (
    <div className="card">
      <h2>Modifier</h2>
      <EntityForm
        initialValue={initial}
        submitLabel="Mettre à jour"
        onSubmit={async (entity) => {
          await updateEntity(entityId, entity);
          navigate("/entities");
        }}
      />
    </div>
  );
}
