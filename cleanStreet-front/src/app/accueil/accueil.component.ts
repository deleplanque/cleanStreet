import {Component, NgZone, OnInit} from '@angular/core';
import {AccueilService} from './accueil.service';
import {} from '@types/googlemaps';
import {Router} from '@angular/router';
import {Signalement} from './Bean/signalement';
import {AlertService} from '../alert/_services/alert.service';
import {Toast, ToasterConfig, ToasterService} from 'angular2-toaster';

declare var google: any;

@Component ({
  selector: 'app-cleanstreet',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css'],
  providers: [AccueilService]
})

export class AccueilComponent implements OnInit {




  geolocationPosition: Position;
  lat: number = 50.6310622 ;
  lng: number = 3.0120553;

  constructor(private accueilService: AccueilService, private toasterService: ToasterService) {}

  signalements: Signalement[];
  isLog: boolean;



  ngOnInit(): void {
    const carte = document.getElementById('carte');
    this.afficheSignalement();
   }


  popToast(type: string, title: string, body: string) {
    const toast: Toast = {
      type: type,
      title: title,
      body: body,
      showCloseButton: true
    };

    this.toasterService.pop(toast);
  }

  afficheSignalement(): void {
    this.accueilService.afficheSignalement()
      .subscribe(data => {
        this.signalements = data;
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
