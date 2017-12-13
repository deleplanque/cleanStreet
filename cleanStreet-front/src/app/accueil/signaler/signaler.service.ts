import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Quartier} from '../Bean/quartier';
import {Localisation} from '../Bean/localisation';
import {User} from '../../authentifiation/bean/user';

@Injectable()
export class SignalerService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private partagerUrl = 'api/ajouterSignalement';

  constructor(private _http: Http) {

  }

  signaler(quartier: Quartier,  description: string, image: string, localisation: Localisation, indiceDeProprete: number, proprietaire: User) {
    const body = {
      quartier: {
        id: quartier.id,
        nom: quartier.nom
      },
      description: description,
      photo: image,
      localisation: {
        latitude: localisation.latitude,
        longitude: localisation.longitude
      },
      indiceDeProprete: indiceDeProprete,
      proprietaire: {
        id: proprietaire.uid,
        nom: proprietaire.unom,
        prenom: proprietaire.uprenom,
        email: proprietaire.umail,
        motDePasse: null,
        signalements: null
      }
    };
    return this._http.post(this.partagerUrl, body);
  }

}
