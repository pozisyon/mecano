import { Component } from '@angular/core';
import {
  IonContent,
  IonHeader,
  IonTitle,
  IonToolbar,
  IonButton,
  IonText
} from '@ionic/angular/standalone';
import { AlertService } from '../../../core/services/alert.service';

@Component({
  selector: 'app-sos-page',
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, IonButton, IonText],
  templateUrl: './sos-page.page.html'
})
export class SosPage {
  confirmationMessage = '';

  constructor(private alertService: AlertService) {}

  sendAlert(): void {
    const alert = this.alertService.sendSosForCurrentSenior();
    if (alert) {
      this.confirmationMessage = alert.message;
    }
  }
}
