import axios from "axios";
const API_BASE = (import.meta as any).env?.VITE_API_BASE || "http://localhost:8080";
export const api = axios.create({ baseURL: API_BASE });
export function setAdminAuth(user:string, pass:string){
  const token = btoa(`${user}:${pass}`);
  api.defaults.headers.common["Authorization"] = `Basic ${token}`;
}
