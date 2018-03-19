
import {Component, OnInit} from '@angular/core';
import {OptimiserService} from './optimiser.service';
import {Signalement} from '../accueil/Bean/signalement';
import {AccueilService} from '../accueil/accueil.service';
import {Router} from '@angular/router';
import {Quartier} from '../accueil/Bean/quartier';
import {Toast, ToasterService} from 'angular2-toaster';
import {EnteteComponent} from '../miseEnPage/entete/entete.component';
declare var $: any;
@Component ({
  selector: 'app-optimiser',
  templateUrl: './optimiser.component.html',
  styleUrls: ['./optimiser.component.css'],
  providers: [OptimiserService, ToasterService, EnteteComponent]
})

export class OptimiserComponent implements OnInit {
  geolocationPosition: Position;
  signalements: Signalement[];
  quartiers: Quartier[];
  perimetre = 100;
  selected = 'Tous';
  lat: number = 50.6062442;
  lng: number = 3.132318;
  user: any;

  constructor(private router: Router, private accueilService: AccueilService, private toasterService: ToasterService, private entete: EnteteComponent) {}

  ngOnInit(): void {
    this.user = JSON.parse(sessionStorage.getItem('utilisateur'));
    if (sessionStorage.getItem('utilisateur') != null && this.user.droit === 10) {
      this.entete.isLog = true;
    } else {
      this.entete.isLog = false;
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
              this.popToast('error', 'Autorisation', 'Pour accéder à la fonction de signalement, CleanStreet a besoin de votre position');
              break;
            case 2:
              console.log('Position Unavailable');
              this.popToast('error', 'Autorisation', 'Oupss ! Votre position n\'est pas disponible');
              break;
            case 3:
              console.log('Timeout');
              this.popToast('error', 'Autorisation', 'Temps dépassé');
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

  supprimerSignalement(signalement): void {
    this.accueilService.supprimerSignalement(signalement)
      .subscribe(data => {
        console.log(data);
        this.signalements = data;
        this.popToast('success', 'Suppression', 'Signalement supprimé avec succés');
      }, error => {
        console.log(error);
        this.popToast('error', 'Suppression', 'Erreur lors de la suppression du signalement');
      });
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

  filtrer(): void {
    if (this.lat == null || this.lng == null) {
      this.popToast('error', 'Autorisation', 'Pour accéder à la fonction de ' +
        'signalement, CleanStreet a besoin de votre position, veuillez modifier les paramètres de votre navigateur');
    } else {
      console.log(this.perimetre);
      console.log(this.lat + ' , ' + this.lng)
      this.accueilService.getSignalementsFiltres(this.perimetre, this.selected, this.lat, this.lng)
        .subscribe(data => {
          this.signalements = data;
          console.log(this.signalements);
        }, error => {
          console.log(error);
        });
    }

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

}
