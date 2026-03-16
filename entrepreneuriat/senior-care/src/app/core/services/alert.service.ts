import { Injectable } from '@angular/core';
import { Alert } from '../models/alert.model';
import { MOCK_ALERTS } from '../data/mock-alerts';
import { SeniorService } from './senior.service';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  private alerts: Alert[] = [...MOCK_ALERTS];

  constructor(private seniorService: SeniorService) {}

  sendSosAlert(seniorId: number): Alert {
    const alert: Alert = {
      id: Date.now(),
      seniorId,
      type: 'SOS',
      message: 'SOS envoyé par le résident.',
      status: 'OPEN',
      createdAt: new Date().toISOString()
    };

    this.alerts.unshift(alert);
    return alert;
  }

  sendSosForCurrentSenior(): Alert | null {
    const senior = this.seniorService.getCurrentSenior();
    if (!senior) return null;

    return this.sendSosAlert(senior.id);
  }

  getAlertsForSenior(seniorId: number): Alert[] {
    return this.alerts
      .filter(alert => alert.seniorId === seniorId)
      .sort((a, b) => b.createdAt.localeCompare(a.createdAt));
  }

  getCurrentSeniorAlerts(): Alert[] {
    const senior = this.seniorService.getCurrentSenior();
    if (!senior) return [];

    return this.getAlertsForSenior(senior.id);
  }

  getAllAlerts(): Alert[] {
    return this.alerts.sort((a, b) => b.createdAt.localeCompare(a.createdAt));
  }

  resolveAlert(alertId: number): void {
    const alert = this.alerts.find(a => a.id === alertId);

    if (alert) {
      alert.status = 'RESOLVED';
    }
  }
}
