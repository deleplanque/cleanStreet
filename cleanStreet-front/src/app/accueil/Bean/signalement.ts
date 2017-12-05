import {Quartier} from './quartier';
import {Localisation} from './localisation';
import {User} from '../../authentifiation/bean/user';

export class Signalement {
  constructor(
    public id: number,
    public quartier: Quartier,
    public description: string,
    public photo: string,
    public localisation: Localisation,
    public indiceDeProprete: number,
    public proprietaire: User
  ) {  }
}
