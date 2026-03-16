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
import { Medication } from '../../../core/models/medication.model';
import { MedicationService } from '../../../core/services/medication.service';
import { SeniorService } from '../../../core/services/senior.service';

@Component({
  selector: 'app-medication-list',
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, IonList, IonItem, IonLabel, IonButton],
  templateUrl: './medication-list.page.html'
})
export class MedicationListPage implements OnInit {
  medications: Medication[] = [];

  constructor(
    private medicationService: MedicationService,
    private seniorService: SeniorService
  ) {}

  ngOnInit(): void {
    const senior = this.seniorService.getCurrentSenior();
    if (senior) {
      this.medications = this.medicationService.getSeniorMedications(senior.id);
    }
  }

  markAsTaken(id: number): void {
    this.medicationService.markAsTaken(id);

    const senior = this.seniorService.getCurrentSenior();
    if (senior) {
      this.medications = this.medicationService.getSeniorMedications(senior.id);
    }
  }
}
