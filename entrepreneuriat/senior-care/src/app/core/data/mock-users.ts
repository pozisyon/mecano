import { User } from '../models/user.model';

export const MOCK_USERS: User[] = [

{
  id: 1,
  firstName: "Jean",
  lastName: "Martin",
  email: "senior@test.com",
  role: "SENIOR",
  phone: "514-000-0001"
},

{
  id: 2,
  firstName: "Marie",
  lastName: "Martin",
  email: "caregiver@test.com",
  role: "CAREGIVER",
  phone: "514-000-0002"
},

{
  id: 3,
  firstName: "Admin",
  lastName: "Residence",
  email: "residence@test.com",
  role: "RESIDENCE_ADMIN",
  phone: "514-000-0003"
}

];
