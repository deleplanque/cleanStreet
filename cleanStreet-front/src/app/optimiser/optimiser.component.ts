
import {Component, OnInit} from '@angular/core';
import {OptimiserService} from './optimiser.service';
import {Signalement} from '../accueil/Bean/signalement';
import {AccueilService} from '../accueil/accueil.service';

@Component ({
  selector: 'app-optimiser',
  templateUrl: './optimiser.component.html',
  styleUrls: ['./optimiser.component.css'],
  providers: [OptimiserService]
})

export class OptimiserComponent implements OnInit {
  geolocationPosition: Position;
  lat: number = 50.6310622 ;
  lng: number = 3.0120553;
  signalements: Signalement[];

  constructor(private accueilService: AccueilService) {}

  ngOnInit(): void {
    this.getSignalements();

    if (window.navigator && window.navigator.geolocation) {
      window.navigator.geolocation.getCurrentPosition(
        position => {
          this.geolocationPosition = position;
          this.lat = position.coords.latitude;
          this.lng = position.coords.longitude;
        },
        error => {
          switch (error.code) {
            case 1:
              console.log('Permission Denied');
              break;
            case 2:
              console.log('Position Unavailable');
              break;
            case 3:
              console.log('Timeout');
              break;
          }
        }
      );
    }

  }

  getSignalements(): void {
    this.accueilService.afficheSignalement()
      .subscribe(data => {
        this.signalements = data;
      }, error => {
        console.log(error);
      });
  }

}
