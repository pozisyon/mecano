import { Component, OnInit } from '@angular/core';
import {
  IonContent,
  IonHeader,
  IonTitle,
  IonToolbar,
  IonButton
} from '@ionic/angular/standalone';
import { RouterLink } from '@angular/router';
import { Residence } from '../../../core/models/residence.model';
import { ResidenceService } from '../../../core/services/residence.service';

@Component({
  selector: 'app-residence-dashboard',
  standalone: true,
  imports: [IonContent, IonHeader, IonTitle, IonToolbar, IonButton, RouterLink],
  templateUrl: './residence-dashboard.page.html'
})
export class ResidenceDashboardPage implements OnInit {
  residence?: Residence;

  constructor(private residenceService: ResidenceService) {}

  ngOnInit(): void {
    this.residence = this.residenceService.getMockCurrentResidence();
  }
}
