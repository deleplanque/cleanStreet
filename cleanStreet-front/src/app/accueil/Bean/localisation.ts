import {Quartier} from './quartier';

export class Localisation {
  constructor(
    public id: number,
    public latitude: number,
    public longitude: number,
    public quartier: Quartier
  ) {  }
}
