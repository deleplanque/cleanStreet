import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

@Injectable()
export class InscriptionService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private inscriptionUrl = 'api/inscription';
  constructor(private _http: Http) {

  }

  inscription(nom: string, prenom: string, mail: string,  pass: string) {
    const body = {
      nom: nom,
      prenom: prenom,
      mail: mail,
      pass: pass
    };
    return this._http.post(this.inscriptionUrl, body);
  }
}
