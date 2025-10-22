export async function getAllMembers() {
  const res = await fetch("/api/members");
  return res.json();
}

export async function searchMembers(params: {
  categorie?: string;
  domaine?: string;
  query?: string;
}) {
  const query = new URLSearchParams(params as Record<string, string>);
  const res = await fetch(`/api/members/search?${query.toString()}`);
  return res.json();
}

const API_BASE = "/api";

export async function adminListAll() {
  const res = await fetch(`${API_BASE}/members`);
  if (!res.ok) throw new Error("Erreur lors du chargement des membres");
  return res.json();
}

export async function adminAdd(member: any) {
  const res = await fetch(`${API_BASE}/members`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(member),
  });
  if (!res.ok) throw new Error("Erreur lors de l’ajout du membre");
  return res.json();
}

export async function adminUpdate(id: number, member: any) {
  const res = await fetch(`${API_BASE}/members/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(member),
  });
  if (!res.ok) throw new Error("Erreur lors de la mise à jour du membre");
  return res.json();
}

export async function adminDelete(id: number) {
  const res = await fetch(`${API_BASE}/members/${id}`, { method: "DELETE" });
  if (!res.ok) throw new Error("Erreur lors de la suppression du membre");
  return res.text();
}

export async function adminToggleRed(id: number, red: boolean) {
  const res = await fetch(`${API_BASE}/members/${id}/red?active=${red}`, {
    method: "PATCH",
  });
  if (!res.ok) throw new Error("Erreur lors de la mise à jour de la liste rouge");
  return res.json();
}

