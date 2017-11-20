import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {HttpClient} from '@angular/common/http';
import {UserInscription} from '../bean/userInscription';
import {User} from '../bean/user';

@Injectable()
export class ConnexionService {

  private connexionUrl = 'api/connexion';

  constructor(private _http: HttpClient) {

  }

  connexion(mail: string,  pass: string) {
    const body = {
      email: mail,
      motDePasse: pass
    };
    return this._http.post<User>(this.connexionUrl, body);
  }
}
