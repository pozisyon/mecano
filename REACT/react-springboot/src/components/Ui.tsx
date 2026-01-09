export function Loading({ label = "Chargement..." }: { label?: string }) {
  return <div className="muted">{label}</div>;
}

export function ErrorBox({ message }: { message: string }) {
  return <div className="errorBox">{message}</div>;
}
