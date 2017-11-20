import {Injectable} from '@angular/core';
<<<<<<< HEAD

import {HttpClient} from '@angular/common/http';
import {User} from '../bean/user';
=======
import {Http} from '@angular/http';
import {HttpClient} from '@angular/common/http';
>>>>>>> 3960f9df127d3a642d17d88ac29e86b56a08c993

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
      motDePasse: pass
    };
    return this._http.post<User>(this.inscriptionUrl, body);
  }
}
