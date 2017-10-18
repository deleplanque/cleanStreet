import { Component } from '@angular/core';
import {AccueilService} from './accueil.service';

@Component ({
  selector: 'app-cleanstreet',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css'],
  providers: [AccueilService]
})
export class AccueilComponent {}
