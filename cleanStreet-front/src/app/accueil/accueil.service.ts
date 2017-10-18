import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

@Injectable()
export class AccueilService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private _http: Http) {

  }

}
