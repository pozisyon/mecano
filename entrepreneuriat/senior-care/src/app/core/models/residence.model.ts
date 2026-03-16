export interface Residence {
  id: number;
  name: string;
  city: string;
  address: string;
  description: string;
  availablePlaces: number;
  monthlyPrice: number;
  services: string[];
  imageUrl?: string;
}
