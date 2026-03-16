import { Injectable } from '@angular/core';
import { Caregiver } from '../models/caregiver.model';
import { Senior } from '../models/senior.model';
import { MOCK_CAREGIVERS } from '../data/mock-caregivers';
import { MockSessionService } from './mock-session.service';
import { SeniorService } from './senior.service';

@Injectable({
  providedIn: 'root'
})
export class CaregiverService {
  private caregivers = [...MOCK_CAREGIVERS];

  constructor(
    private sessionService: MockSessionService,
    private seniorService: SeniorService
  ) {}

  getCurrentCaregiver(): Caregiver | null {
    const currentUser = this.sessionService.getCurrentUser();
    if (!currentUser) return null;

    return this.caregivers.find(c => c.userId === currentUser.id) ?? null;
  }

  getLinkedSeniors(): Senior[] {
    const caregiver = this.getCurrentCaregiver();
    if (!caregiver) return [];

    return caregiver.linkedSeniorIds
      .map(id => this.seniorService.getSeniorById(id))
      .filter((senior): senior is Senior => !!senior);
  }

  getCaregiverByUserId(userId: number): Caregiver | undefined {
    return this.caregivers.find(c => c.userId === userId);
  }
}
