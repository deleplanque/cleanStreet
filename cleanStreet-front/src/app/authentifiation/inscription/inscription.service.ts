import {Injectable} from '@angular/core';
import {User} from '../bean/user';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class InscriptionService {

  private inscriptionUrl = 'api/inscription';

  constructor(private _http: HttpClient) {

  }

  inscription(nom: string, prenom: string, mail: string,  pass: string) {
    console.log('nom', nom);
    const body = {
      nom: nom,
      prenom: prenom,
      email: mail,
      motDePasse: pass,
      droit: 1
    };
    return this._http.post<User>(this.inscriptionUrl, body);
  }
}
