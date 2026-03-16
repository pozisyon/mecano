import { Injectable } from '@angular/core';
import { Medication } from '../models/medication.model';
import { MOCK_MEDICATIONS } from '../data/mock-medications';
import { SeniorService } from './senior.service';

@Injectable({
  providedIn: 'root'
})
export class MedicationService {
  private medications = [...MOCK_MEDICATIONS];

  constructor(private seniorService: SeniorService) {}

  getSeniorMedications(seniorId: number): Medication[] {
    return this.medications.filter(m => m.seniorId === seniorId);
  }

  getCurrentSeniorMedications(): Medication[] {
    const senior = this.seniorService.getCurrentSenior();
    if (!senior) return [];

    return this.getSeniorMedications(senior.id);
  }

  getMedicationById(medicationId: number): Medication | undefined {
    return this.medications.find(m => m.id === medicationId);
  }

  markAsTaken(medicationId: number): void {
    const medication = this.getMedicationById(medicationId);

    if (medication) {
      medication.taken = true;

      if (medication.stock > 0) {
        medication.stock -= 1;
      }
    }
  }

  resetTakenStatus(medicationId: number): void {
    const medication = this.getMedicationById(medicationId);

    if (medication) {
      medication.taken = false;
    }
  }

  getLowStockMedications(seniorId: number, threshold = 5): Medication[] {
    return this.getSeniorMedications(seniorId).filter(m => m.stock <= threshold);
  }
}
