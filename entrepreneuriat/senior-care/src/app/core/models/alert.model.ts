export interface Alert {
  id: number;
  seniorId: number;
  type: 'SOS' | 'MEDICATION_MISSED' | 'LOW_STOCK';
  message: string;
  status: 'OPEN' | 'RESOLVED';
  createdAt: string;
}
