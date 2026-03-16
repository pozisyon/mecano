import { Appointment } from '../models/appointment.model';

export const MOCK_APPOINTMENTS: Appointment[] = [

{
  id: 1,
  seniorId: 1,
  title: "Médecin de famille",
  date: "2026-03-25",
  time: "10:30",
  location: "Clinique Santé Montréal"
},

{
  id: 2,
  seniorId: 1,
  title: "Pharmacie - renouvellement",
  date: "2026-03-28",
  time: "14:00",
  location: "Pharmaprix"
}

];
