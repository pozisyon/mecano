import { AppNotification } from '../models/notification.model';

export const MOCK_NOTIFICATIONS: AppNotification[] = [

{
  id: 1,
  userId: 2,
  title: "Alerte médicament",
  message: "Jean Martin a oublié un médicament.",
  read: false,
  createdAt: "2026-03-14"
},

{
  id: 2,
  userId: 3,
  title: "Nouvelle demande",
  message: "Une demande d'admission a été reçue.",
  read: false,
  createdAt: "2026-03-14"
}

];
