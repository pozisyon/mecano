import { Residence } from '../models/residence.model';

export const MOCK_RESIDENCES: Residence[] = [

{
  id: 1,
  name: "Résidence Les Jardins",
  city: "Montreal",
  address: "123 Rue Sainte-Catherine",
  description: "Résidence moderne avec soins infirmiers.",
  availablePlaces: 4,
  monthlyPrice: 2200,
  services: ["Repas", "Soins infirmiers", "Activités"],
  imageUrl: "assets/res1.jpg"
},

{
  id: 2,
  name: "Résidence Soleil d'Or",
  city: "Laval",
  address: "456 Boulevard Laval",
  description: "Milieu calme et sécurisé.",
  availablePlaces: 2,
  monthlyPrice: 2000,
  services: ["Repas", "Activités sociales"],
  imageUrl: "assets/res2.jpg"
},

{
  id: 3,
  name: "Résidence Tranquillité",
  city: "Trois-Rivières",
  address: "78 Rue des Aînés",
  description: "Résidence spécialisée soins longue durée.",
  availablePlaces: 0,
  monthlyPrice: 2400,
  services: ["Soins infirmiers", "Physiothérapie"],
  imageUrl: "assets/res3.jpg"
}

];
