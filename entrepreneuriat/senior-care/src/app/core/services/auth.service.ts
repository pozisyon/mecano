import { Injectable, computed } from '@angular/core';
import { User, UserRole } from '../models/user.model';
import { MOCK_USERS } from '../data/mock-users';
import { MockSessionService } from './mock-session.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private sessionService: MockSessionService) {}

  currentUser = computed(() => this.sessionService.currentUser());
  isLoggedIn = computed(() => this.sessionService.isLoggedIn());

  loginAsRole(role: UserRole): User | null {
    const user = MOCK_USERS.find(u => u.role === role) ?? null;

    if (user) {
      this.sessionService.setCurrentUser(user);
    }

    return user;
  }

  loginAsSenior(): User | null {
    return this.loginAsRole('SENIOR');
  }

  loginAsCaregiver(): User | null {
    return this.loginAsRole('CAREGIVER');
  }

  loginAsResidenceAdmin(): User | null {
    return this.loginAsRole('RESIDENCE_ADMIN');
  }

  getCurrentUser(): User | null {
    return this.sessionService.getCurrentUser();
  }

  logout(): void {
    this.sessionService.clearSession();
  }
}
