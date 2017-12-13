import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

@Injectable()
export class SignalerService {

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private _http: Http) {

  }

}
