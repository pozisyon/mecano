export interface Senior {
  id: number;
  userId: number;
  age: number;
  city: string;
  livingMode: 'HOME' | 'WAITING_RESIDENCE' | 'RESIDENCE';
  autonomyLevel: 'LOW' | 'MEDIUM' | 'HIGH';
  emergencyContactName: string;
  emergencyContactPhone: string;
}
