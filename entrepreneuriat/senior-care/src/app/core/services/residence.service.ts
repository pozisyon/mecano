import { Injectable } from '@angular/core';
import { Residence } from '../models/residence.model';
import { MOCK_RESIDENCES } from '../data/mock-residences';

@Injectable({
  providedIn: 'root'
})
export class ResidenceService {
  private residences: Residence[] = [...MOCK_RESIDENCES];

  getResidences(): Residence[] {
    return this.residences;
  }

  getResidenceById(id: number): Residence | undefined {
    return this.residences.find(residence => residence.id === id);
  }

  searchByCity(city: string): Residence[] {
    if (!city.trim()) return this.residences;

    return this.residences.filter(residence =>
      residence.city.toLowerCase().includes(city.toLowerCase())
    );
  }

  getAvailableResidences(): Residence[] {
    return this.residences.filter(residence => residence.availablePlaces > 0);
  }

  getMockCurrentResidence(): Residence | undefined {
    return this.residences[0];
  }
}
