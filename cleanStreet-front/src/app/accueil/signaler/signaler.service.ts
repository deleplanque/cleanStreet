import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {Quartier} from '../Bean/quartier';
import {Localisation} from '../Bean/localisation';
import {User} from '../../authentifiation/bean/user';
import {Signalement} from '../Bean/signalement';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {ReponseCreation} from '../Bean/reponseCreation';

@Injectable()
export class SignalerService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private partagerUrl = 'api/ajouterSignalement';
  private getQuartierParNomUrl = 'api/getQuartierParNom';

  constructor(private _http: HttpClient) {

  }


  getQuartierParNom(nom: string): Observable<Quartier> {
    const url = `${this.getQuartierParNomUrl}/${nom}`;
    return this._http.get<Quartier>(url);
  }

  signaler(quartier: Quartier,  description: string, image: string, photoBase64: string,
           localisation: Localisation, indiceDeProprete: number, proprietaire: User): Observable<ReponseCreation> {
    const body = {
      quartier: {
        id: quartier.id,
        nom: quartier.nom
      },
      description: description,
      photo: image,
      photoBase64: photoBase64,
      localisation: {
        latitude: localisation.latitude,
        longitude: localisation.longitude
      },
      indiceDeProprete: indiceDeProprete,
      proprietaire: {
        id: proprietaire.id,
        nom: proprietaire.nom,
        prenom: proprietaire.prenom,
        email: proprietaire.mail,
        motDePasse: null,
        signalements: null
      }
    };
    return this._http.post<ReponseCreation>(this.partagerUrl, body);
  }



}
