import { useState } from "react";
import type { Entity } from "../models/entity";

export default function EntityForm({
  initialValue,
  submitLabel,
  onSubmit,
}: {
  initialValue: Entity;
  submitLabel: string;
  onSubmit: (entity: Entity) => Promise<void>;
}) {
  const [form, setForm] = useState<Entity>(initialValue);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

 const change = (e: React.ChangeEvent<HTMLInputElement>) => {
   const { name, value } = e.target;
   setForm((prev) => ({ ...prev, [name]: value }));
 };


  const submit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);

    // validation simple
    if (!form.nom.trim()) return setError("Le champ nom est requis.");
    if (!form.prenom.trim()) return setError("Le champ prénom est requis.");
    if (!form.email.trim()) return setError("Le champ email est requis.");

    setLoading(true);
    try {
      await onSubmit(form);
    } catch {
      setError("Une erreur est survenue lors de l'enregistrement.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <form className="form" onSubmit={submit}>
      {error && <div className="errorBox">{error}</div>}

      <label>
        Nom
        <input name="nom" value={form.nom} onChange={change} placeholder="Nom" />
      </label>

      <label>
        Prénom
        <input
          name="prenom"
          value={form.prenom}
          onChange={change}
          placeholder="Prénom"
        />
      </label>

      <label>
        Email
        <input name="email" value={form.email} onChange={change} placeholder="email@exemple.com" />
      </label>

      <button className="btn" type="submit" disabled={loading}>
        {loading ? "..." : submitLabel}
      </button>
    </form>
  );
}
