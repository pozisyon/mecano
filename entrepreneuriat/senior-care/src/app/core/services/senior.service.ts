import { Injectable } from '@angular/core';
import { Senior } from '../models/senior.model';
import { MOCK_SENIORS } from '../data/mock-senior';
import { MockSessionService } from './mock-session.service';

@Injectable({
  providedIn: 'root'
})
export class SeniorService {
  private seniors = [...MOCK_SENIORS];

  constructor(private sessionService: MockSessionService) {}

  getCurrentSenior(): Senior | null {
    const currentUser = this.sessionService.getCurrentUser();
    if (!currentUser) return null;

    return this.seniors.find(s => s.userId === currentUser.id) ?? null;
  }

  getSeniorById(id: number): Senior | undefined {
    return this.seniors.find(s => s.id === id);
  }

  getSeniorByUserId(userId: number): Senior | undefined {
    return this.seniors.find(s => s.userId === userId);
  }

  getAllSeniors(): Senior[] {
    return this.seniors;
  }
}
