import { Injectable } from '@angular/core';
import { Appointment } from '../models/appointment.model';
import { MOCK_APPOINTMENTS } from '../data/mock-appointments';
import { SeniorService } from './senior.service';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private appointments: Appointment[] = [...MOCK_APPOINTMENTS];

  constructor(private seniorService: SeniorService) {}

  getSeniorAppointments(seniorId: number): Appointment[] {
    return this.appointments
      .filter(appointment => appointment.seniorId === seniorId)
      .sort((a, b) => {
        const aDate = `${a.date}T${a.time}`;
        const bDate = `${b.date}T${b.time}`;
        return aDate.localeCompare(bDate);
      });
  }

  getCurrentSeniorAppointments(): Appointment[] {
    const senior = this.seniorService.getCurrentSenior();
    if (!senior) return [];

    return this.getSeniorAppointments(senior.id);
  }

  getAppointmentById(appointmentId: number): Appointment | undefined {
    return this.appointments.find(appointment => appointment.id === appointmentId);
  }
}
