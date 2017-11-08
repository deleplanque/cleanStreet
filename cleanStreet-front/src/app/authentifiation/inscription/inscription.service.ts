import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class InscriptionService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private inscriptionUrl = 'api/inscription';

  constructor(private _http: HttpClient) {

  }

  inscription(nom: string, prenom: string, mail: string,  pass: string) {
    console.log('nom', nom);
    const body = {
      nom: nom,
      prenom: prenom,
      mail: mail,
      pass: pass
    };
    return this._http.post(this.inscriptionUrl, body);
  }
}
