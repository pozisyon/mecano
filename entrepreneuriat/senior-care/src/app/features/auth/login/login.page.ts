import { Component } from '@angular/core';
import { Router } from '@angular/router';
import {
  IonContent,
  IonHeader,
  IonTitle,
  IonToolbar,
  IonButton
} from '@ionic/angular/standalone';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, IonButton],
  templateUrl: './login.page.html'
})
export class LoginPage {
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  loginAsSenior(): void {
    this.authService.loginAsSenior();
    this.router.navigateByUrl('/senior/dashboard');
  }

  loginAsCaregiver(): void {
    this.authService.loginAsCaregiver();
    this.router.navigateByUrl('/caregiver/dashboard');
  }

  loginAsResidenceAdmin(): void {
    this.authService.loginAsResidenceAdmin();
    this.router.navigateByUrl('/residence/dashboard');
  }
}
