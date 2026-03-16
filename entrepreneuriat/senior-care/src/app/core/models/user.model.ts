export type UserRole = 'SENIOR' | 'CAREGIVER' | 'RESIDENCE_ADMIN';

export interface User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  role: UserRole;
   phone?: string;
}
