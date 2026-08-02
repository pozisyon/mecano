import { Routes } from '@angular/router';
import { LandingPage } from './pages/landing.page';
import { UserDashboardPage } from './pages/user-dashboard.page';
import { AdminDashboardPage } from './pages/admin-dashboard.page';

export const routes: Routes = [
  { path: '', component: LandingPage },
  { path: 'dashboard', component: UserDashboardPage },
  { path: 'admin', component: AdminDashboardPage },
  { path: '**', redirectTo: '' }
];
