import {Component, NgZone, OnInit} from '@angular/core';
import {} from '@types/googlemaps';
import {Router} from '@angular/router';
import {Signalement} from './Bean/signalement';
import {AlertService} from '../alert/_services/alert.service';
import {AccueilService} from '../accueil/accueil.service';
import {ToasterService} from 'angular2-toaster';


@Component ({
  selector: 'app-cleanstreet',
  templateUrl: './sensibiliser.component.html',
  styleUrls: ['./sensibiliser.component.css'],
  providers: [AccueilService, ToasterService]
})

export class SensibiliserComponent implements OnInit {

  signalement: Signalement;
  isSelected = false;
  geolocationPosition: Position;
  lat: number = 50.6310622 ;
  lng: number = 3.0120553;

  constructor(private router: Router, private accueilService: AccueilService, private alertService: AlertService, private toasterService: ToasterService) {}

  signalements: Signalement[];
  isLog: boolean;



  ngOnInit(): void {
    if (sessionStorage.getItem('utilisateur') != null) {
      this.isLog = true;
    } else {
      this.isLog = false;
      this.router.navigate(['/accueil']);
    }

    const carte = document.getElementById('carte');
    this.afficheSignalement();

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

  afficheSignalement(): void {
    this.accueilService.afficheSignalement()
      .subscribe(data => {
        this.signalements = data;
      }, error => {
        console.log(error);
      });
  }

  markerClick(m): void {
    console.log(m);
    this.accueilService.getSignalementById(m.id)
      .subscribe(data => {
        this.signalement = data;
        this.isSelected = true;
      }, error => {
        console.log(error);
      });
  }

  getWindowHeight() {
    let windowHeight = 0;
    if (typeof(window.innerHeight) === 'number') {
      windowHeight = window.innerHeight;
    } else {
      if (document.documentElement && document.documentElement.clientHeight) {
        windowHeight = document.documentElement.clientHeight;
      } else {
        if (document.body && document.body.clientHeight) {
          windowHeight = document.body.clientHeight;
        }
      }
    }
    return windowHeight;
  }
}
