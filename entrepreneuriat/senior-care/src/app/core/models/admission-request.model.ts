export interface AdmissionRequest {
  id: number;
  seniorId: number;
  residenceId: number;
  status: 'PENDING' | 'WAITLISTED' | 'ACCEPTED' | 'REJECTED';
  requestedAt: string;
}
