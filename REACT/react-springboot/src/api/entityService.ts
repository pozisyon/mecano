import type { Entity } from "../models/entity";
import { http } from "./http";

const PATH = "/api/entities";

export const getEntities = () => http.get<Entity[]>(PATH);
export const getEntityById = (id: number) => http.get<Entity>(`${PATH}/${id}`);
export const createEntity = (entity: Entity) => http.post<Entity>(PATH, entity);
export const updateEntity = (id: number, entity: Entity) => http.put<Entity>(`${PATH}/${id}`, entity);
export const deleteEntity = (id: number) => http.delete<void>(`${PATH}/${id}`);
