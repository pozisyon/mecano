import { Component, OnInit } from '@angular/core';
import {
  IonContent,
  IonHeader,
  IonTitle,
  IonToolbar,
  IonList,
  IonItem,
  IonLabel,
  IonButton
} from '@ionic/angular/standalone';
import { AdmissionRequest } from '../../../core/models/admission-request.model';
import { AdmissionService } from '../../../core/services/admission.service';

@Component({
  selector: 'app-residence-requests',
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, IonList, IonItem, IonLabel, IonButton],
  templateUrl: './residence-requests.page.html'
})
export class ResidenceRequestsPage implements OnInit {
  requests: AdmissionRequest[] = [];

  constructor(private admissionService: AdmissionService) {}

  ngOnInit(): void {
    this.requests = this.admissionService.getRequestsForResidence(1);
  }

  accept(requestId: number): void {
    this.admissionService.updateRequestStatus(requestId, 'ACCEPTED');
    this.requests = this.admissionService.getRequestsForResidence(1);
  }

  reject(requestId: number): void {
    this.admissionService.updateRequestStatus(requestId, 'REJECTED');
    this.requests = this.admissionService.getRequestsForResidence(1);
  }
}
