import { Injectable } from '@angular/core';
import { AdmissionRequest } from '../models/admission-request.model';
import { MOCK_ADMISSIONS } from '../data/mock-admission-requests';
import { SeniorService } from './senior.service';

@Injectable({
  providedIn: 'root'
})
export class AdmissionService {
  private requests: AdmissionRequest[] = [...MOCK_ADMISSIONS];

  constructor(private seniorService: SeniorService) {}

  createAdmissionRequest(seniorId: number, residenceId: number): AdmissionRequest {
    const newRequest: AdmissionRequest = {
      id: Date.now(),
      seniorId,
      residenceId,
      status: 'PENDING',
      requestedAt: new Date().toISOString()
    };

    this.requests.unshift(newRequest);
    return newRequest;
  }

  createRequestForCurrentSenior(residenceId: number): AdmissionRequest | null {
    const senior = this.seniorService.getCurrentSenior();
    if (!senior) return null;

    return this.createAdmissionRequest(senior.id, residenceId);
  }

  getRequestsForSenior(seniorId: number): AdmissionRequest[] {
    return this.requests
      .filter(request => request.seniorId === seniorId)
      .sort((a, b) => b.requestedAt.localeCompare(a.requestedAt));
  }

  getRequestsForCurrentSenior(): AdmissionRequest[] {
    const senior = this.seniorService.getCurrentSenior();
    if (!senior) return [];

    return this.getRequestsForSenior(senior.id);
  }

  getRequestsForResidence(residenceId: number): AdmissionRequest[] {
    return this.requests
      .filter(request => request.residenceId === residenceId)
      .sort((a, b) => b.requestedAt.localeCompare(a.requestedAt));
  }

  updateRequestStatus(
    requestId: number,
    status: 'PENDING' | 'WAITLISTED' | 'ACCEPTED' | 'REJECTED'
  ): void {
    const request = this.requests.find(r => r.id === requestId);

    if (request) {
      request.status = status;
    }
  }

  getRequestById(requestId: number): AdmissionRequest | undefined {
    return this.requests.find(request => request.id === requestId);
  }
}
