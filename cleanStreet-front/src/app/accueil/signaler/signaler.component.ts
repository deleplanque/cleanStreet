///<reference path="../../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import { Component } from '@angular/core';
import {SignalerService} from './signaler.service';

@Component ({
  selector: 'app-signaler',
  templateUrl: './signaler.component.html',
  styleUrls: ['../accueil.component.css'],
  providers: [SignalerService]
})
export class SignalerComponent {


}
