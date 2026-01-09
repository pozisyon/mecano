import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import type { Entity } from "../../models/entity";
import { deleteEntity, getEntities } from "../../api/entityService";
import { ErrorBox, Loading } from "../../components/Ui";

export default function EntityListPage() {
  const [items, setItems] = useState<Entity[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const load = async () => {
    setError(null);
    setLoading(true);
    try {
      const res = await getEntities();
      setItems(res.data);
    } catch {
      setError("Impossible de charger la liste.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { load(); }, []);

  const onDelete = async (id?: number) => {
    if (!id) return;
    if (!confirm("Supprimer cet élément ?")) return;

    try {
      await deleteEntity(id);
      load();
    } catch {
      alert("Suppression échouée.");
    }
  };

  return (
    <div className="card">
      <div className="row space">
        <h2>Entités</h2>
        <Link className="btn" to="/entities/new">+ Ajouter</Link>
      </div>

      {loading && <Loading />}
      {error && <ErrorBox message={error} />}

      {!loading && !error && (
        <div className="tableWrap">
          <table className="table">
            <thead>
              <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Actions</th>
              </tr>
            </thead>

            <tbody>
              {items.map((e) => (
                <tr key={e.id}>
                  <td>{e.name}</td>
                  <td className="truncate">{e.email}</td>
                  <td className="actions">
                    <Link className="btnGhost" to={`/entities/${e.id}/edit`}>
                      Modifier
                    </Link>
                    <button className="btnDanger" onClick={() => onDelete(e.id)}>
                      Supprimer
                    </button>
                  </td>
                </tr>
              ))}

              {items.length === 0 && (
                <tr>
                  <td colSpan={3} className="muted">Aucun élément</td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
