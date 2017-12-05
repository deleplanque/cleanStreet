import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {HttpClient} from '@angular/common/http';
import {Signalement} from './Bean/signalement';

@Injectable()
export class AccueilService {

  private headers = new Headers({'Content-Type': 'application/json'});

  private afficheSignalementUrl = 'api/afficheSignalement';

  constructor(private _http: HttpClient) {
      }


  afficheSignalement() {
    return this._http.get<Signalement[]>(this.afficheSignalementUrl);
  }
}
