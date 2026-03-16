import { Injectable, signal, computed } from '@angular/core';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class MockSessionService {
  private readonly STORAGE_KEY = 'mock_current_user';

  private currentUserSignal = signal<User | null>(this.loadStoredUser());

  currentUser = computed(() => this.currentUserSignal());
  isLoggedIn = computed(() => this.currentUserSignal() !== null);

  setCurrentUser(user: User): void {
    this.currentUserSignal.set(user);
    localStorage.setItem(this.STORAGE_KEY, JSON.stringify(user));
  }

  getCurrentUser(): User | null {
    return this.currentUserSignal();
  }

  clearSession(): void {
    this.currentUserSignal.set(null);
    localStorage.removeItem(this.STORAGE_KEY);
  }

  private loadStoredUser(): User | null {
    const raw = localStorage.getItem(this.STORAGE_KEY);
    return raw ? JSON.parse(raw) as User : null;
  }
}
