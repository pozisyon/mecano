import { Component, OnInit } from '@angular/core';
import {
  IonContent,
  IonHeader,
  IonTitle,
  IonToolbar,
  IonList,
  IonItem,
  IonLabel
} from '@ionic/angular/standalone';
import { Appointment } from '../../../core/models/appointment.model';
import { AppointmentService } from '../../../core/services/appointment.service';
import { SeniorService } from '../../../core/services/senior.service';

@Component({
  selector: 'app-appointment-list',
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, IonList, IonItem, IonLabel],
  templateUrl: './appointment-list.page.html'
})
export class AppointmentListPage implements OnInit {
  appointments: Appointment[] = [];

  constructor(
    private appointmentService: AppointmentService,
    private seniorService: SeniorService
  ) {}

  ngOnInit(): void {
    const senior = this.seniorService.getCurrentSenior();
    if (senior) {
      this.appointments = this.appointmentService.getSeniorAppointments(senior.id);
    }
  }
}
