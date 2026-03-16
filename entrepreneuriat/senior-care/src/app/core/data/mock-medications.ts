import { Medication } from '../models/medication.model';

export const MOCK_MEDICATIONS: Medication[] = [

{
  id: 1,
  seniorId: 1,
  name: "Paracétamol",
  dosage: "500mg",
  schedule: "08:00",
  stock: 12,
  taken: false
},

{
  id: 2,
  seniorId: 1,
  name: "Vitamine D",
  dosage: "1000 UI",
  schedule: "12:00",
  stock: 20,
  taken: true
},

{
  id: 3,
  seniorId: 1,
  name: "Amlodipine",
  dosage: "5mg",
  schedule: "20:00",
  stock: 5,
  taken: false
}

];
