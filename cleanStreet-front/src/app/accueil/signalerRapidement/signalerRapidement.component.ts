///<reference path="../../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component, OnInit} from '@angular/core';
import {SignalerService} from './signalerRapidement.service';
import {Signalement} from '../Bean/signalement';
import {AccueilService} from '../accueil.service';

@Component ({
  selector: 'app-signaler',
  templateUrl: './signalerRapidement.component.html',
  styleUrls: ['../accueil.component.css'],
  providers: [SignalerService]
})
export class SignalerComponent implements OnInit {
  imageSrc: string = 'assets/images/noimage.jpg';
  geolocationPosition: Position;
  lat: number = 50.6310622 ;
  lng: number = 3.0120553;

  constructor(private accueilService: AccueilService) {}

  signalements: Signalement[];

  ngOnInit(): void {
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

  displayPhoto(fileInput) {
    if (fileInput.target.files && fileInput.target.files[0]) {
      const reader = new FileReader();

      reader.onload = ((e) => {
        this.imageSrc = e.target['result'];
      });

      reader.readAsDataURL(fileInput.target.files[0]);
    }
  }
}
