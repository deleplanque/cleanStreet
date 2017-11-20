import {Injectable} from '@angular/core';

import {HttpClient} from '@angular/common/http';
import {User} from '../bean/user';

@Injectable()
export class InscriptionService {

  private inscriptionUrl = 'api/inscription';

  constructor(private _http: HttpClient) {

  }

  inscription(nom: string, prenom: string, mail: string,  pass: string) {
    const body = {
      nom: nom,
      prenom: prenom,
      email: mail,
      motDePasse: pass
    };
    return this._http.post<User>(this.inscriptionUrl, body);
  }
}
