import { Component } from '@angular/core';
import {
  IonContent,
  IonHeader,
  IonTitle,
  IonToolbar,
  IonButton
} from '@ionic/angular/standalone';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-senior-dashboard',
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, IonButton, RouterLink],
  templateUrl: './senior-dashboard.page.html'
})
export class SeniorDashboardPage {}
