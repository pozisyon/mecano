import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadComponent: () =>
      import('./features/auth/login/login.page').then(m => m.LoginPage)
  },
  {
    path: 'senior/dashboard',
    loadComponent: () =>
      import('./features/seniors/senior-dashboard/senior-dashboard.page').then(
        m => m.SeniorDashboardPage
      )
  },
  {
    path: 'senior/medications',
    loadComponent: () =>
      import('./features/medications/medication-list/medication-list.page').then(
        m => m.MedicationListPage
      )
  },
  {
    path: 'senior/appointments',
    loadComponent: () =>
      import('./features/appointments/appointment-list/appointment-list.page').then(
        m => m.AppointmentListPage
      )
  },
  {
    path: 'senior/sos',
    loadComponent: () =>
      import('./features/alerts/sos-page/sos-page.page').then(m => m.SosPage)
  },
  {
    path: 'caregiver/dashboard',
    loadComponent: () =>
      import('./features/caregivers/caregiver-dashboard/caregiver-dashboard.page').then(
        m => m.CaregiverDashboardPage
      )
  },
  {
    path: 'residence/dashboard',
    loadComponent: () =>
      import('./features/residences/residence-dashboard/residence-dashboard.page').then(
        m => m.ResidenceDashboardPage
      )
  },
  {
    path: 'residence/requests',
    loadComponent: () =>
      import('./features/residences/residence-requests/residence-requests.page').then(
        m => m.ResidenceRequestsPage
      )
  },
  {
    path: '**',
    redirectTo: 'login'
  }
];
