export interface Medication {
  id: number;
  seniorId: number;
  name: string;
  dosage: string;
  schedule: string;
  stock: number;
  taken: boolean;
}
