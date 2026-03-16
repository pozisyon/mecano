import { Component, OnInit } from '@angular/core';
import {
  IonContent,
  IonHeader,
  IonTitle,
  IonToolbar
} from '@ionic/angular/standalone';
import { CaregiverService } from '../../../core/services/caregiver.service';
import { Senior } from '../../../core/models/senior.model';

@Component({
  selector: 'app-caregiver-dashboard',
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar],
  templateUrl: './caregiver-dashboard.page.html'
})
export class CaregiverDashboardPage implements OnInit {
  linkedSeniors: Senior[] = [];

  constructor(private caregiverService: CaregiverService) {}

  ngOnInit(): void {
    this.linkedSeniors = this.caregiverService.getLinkedSeniors();
  }
}
