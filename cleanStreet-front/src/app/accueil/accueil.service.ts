import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import {HttpClient} from '@angular/common/http';
import {Signalement} from './Bean/signalement';
import {Observable} from 'rxjs/Observable';
import {Quartier} from './Bean/quartier';

@Injectable()
export class AccueilService {

  private headers = new Headers({'Content-Type': 'application/json'});

  private afficheSignalementUrl = 'api/afficheSignalement';
  private getQuartiertUrl = 'api/getQuartiers';
  private getSignalementByIdUrl = 'api/getSignalementById';
  private getSignalementsFiltresUrl = 'api/getSignalementsFiltres';
  private supprimerSignalementUrl = 'api/supprimerSignalement';
  constructor(private _http: HttpClient) {
      }


  afficheSignalement() {
    return this._http.get<Signalement[]>(this.afficheSignalementUrl);
  }

  getQuartier() {
    return this._http.get<Quartier[]>(this.getQuartiertUrl);
  }


  getSignalementById(id: number): Observable<Signalement> {
    const url = `${this.getSignalementByIdUrl}/${id}`;
    return this._http.get<Signalement>(url);
  }

  getSignalementsFiltres(perimetre: number, quartier: string, lat: number, lng: number): Observable<Signalement[]> {
    const body = {
      perimetre: perimetre,
      quartier: quartier,
      lat: lat,
      lng: lng
    };
    return this._http.post<Signalement[]>(this.getSignalementsFiltresUrl, body);
  }

  supprimerSignalement(signalement): Observable<Signalement[]> {
    return this._http.post<Signalement[]>(this.supprimerSignalementUrl, signalement);
  }
}
