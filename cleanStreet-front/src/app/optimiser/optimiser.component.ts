
import {Component, OnInit} from '@angular/core';
import {OptimiserService} from './optimiser.service';
import {Signalement} from '../accueil/Bean/signalement';
import {AccueilService} from '../accueil/accueil.service';
import {Router} from '@angular/router';
import {Quartier} from '../accueil/Bean/quartier';
declare var $: any;
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
  quartiers: Quartier[];
  isLog: boolean;
  selectedQuartier: string;

  constructor(private router: Router, private accueilService: AccueilService) {}

  ngOnInit(): void {
    if (sessionStorage.getItem('utilisateur') != null) {
      this.isLog = true;
    } else {
      this.isLog = false;
      this.router.navigate(['/accueil']);
    }

    this.getSignalements();
    this.getQuartier();

    $('select').material_select();

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

  onChange(newValue) {
    console.log(newValue);
    this.selectedQuartier = newValue;
  }

  getQuartier(): void {
    this.accueilService.getQuartier()
      .subscribe(data => {
        this.quartiers = data;
        console.log(this.quartiers);
      }, error => {
        console.log(error);
      });
  }

}
