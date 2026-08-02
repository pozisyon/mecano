import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import {
  IonContent,
  IonCard,
  IonCardContent,
  IonItem,
  IonLabel,
  IonInput,
  IonButton,
  IonIcon,
  IonSpinner,
  IonText,
  IonSelect,
  IonSelectOption
} from '@ionic/angular/standalone';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterLink,
    IonContent,
    IonCard,
    IonCardContent,
    IonItem,
    IonLabel,
    IonInput,
    IonButton,
    IonIcon,
    IonSpinner,
    IonText,
    IonSelect,
    IonSelectOption
  ]
})
export class RegisterPage {
  fullName = '';
  email = '';
  password = '';
  role: 'DRIVER' | 'MECHANIC' = 'DRIVER';

  loading = false;
  errorMessage = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  register(): void {
    this.errorMessage = '';

    if (!this.fullName || !this.email || !this.password || !this.role) {
      this.errorMessage = 'All fields are required.';
      return;
    }

    this.loading = true;

    this.authService.register({
      fullName: this.fullName,
      email: this.email,
      password: this.password,
      role: this.role
    }).subscribe({
      next: response => {
        this.loading = false;

        if (response.data.role === 'DRIVER') {
          this.router.navigateByUrl('/driver/dashboard');
          return;
        }

        this.router.navigateByUrl('/mechanic/dashboard');
      },
      error: () => {
        this.loading = false;
        this.errorMessage = 'Unable to create account.';
      }
    });
  }
}
