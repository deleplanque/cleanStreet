import {Component, OnInit} from '@angular/core';
import {AccueilService} from './accueil.service';
import {} from '@types/googlemaps';
import {Router} from '@angular/router';
import {Signalement} from './Bean/signalement';

declare var google: any;

@Component ({
  selector: 'app-cleanstreet',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css'],
  providers: [AccueilService]
})

export class AccueilComponent implements OnInit {

  constructor(private accueilService: AccueilService, private router: Router) {}

  signalements: Signalement[];

  ngOnInit(): void {
    this.afficheSignalement();
  }

  afficheSignalement(): void {
    this.accueilService.afficheSignalement()
      .subscribe(data => {
        this.signalements = data;
      }, error => {
        console.log(error);
      });
  }

  title: string = 'Google Maps Addeed Successfully';

  lat: number = 50.6310622 ;

  lng: number = 3.0120553;


}
