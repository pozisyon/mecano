import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ApiResponse } from '../../models/api-response.model';
import { AuthResponse } from '../../models/auth-response.model';
import { LoginRequest } from '../../models/login-request.model';
import { RegisterRequest } from '../../models/register-request.model';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly apiUrl = `${environment.apiUrl}/auth`;

  constructor(
    private http: HttpClient,
    private storageService: StorageService
  ) {}

  login(request: LoginRequest): Observable<ApiResponse<AuthResponse>> {
    return this.http.post<ApiResponse<AuthResponse>>(
      `${this.apiUrl}/login`,
      request
    ).pipe(
      tap(response => this.saveSession(response.data))
    );
  }

  register(request: RegisterRequest): Observable<ApiResponse<AuthResponse>> {
    return this.http.post<ApiResponse<AuthResponse>>(
      `${this.apiUrl}/register`,
      request
    ).pipe(
      tap(response => this.saveSession(response.data))
    );
  }

  logout(): void {
    this.storageService.clear();
  }

  private saveSession(data: AuthResponse): void {
    this.storageService.setToken(data.token);
    this.storageService.setUser({
      id: data.id,
      email: data.email,
      role: data.role
    });
  }
}
