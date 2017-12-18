///<reference path="../../../../node_modules/@angular/core/src/metadata/directives.d.ts"/>
import {Component, Input, OnInit} from '@angular/core';
import {SignalerService} from './signaler.service';
import {Signalement} from '../Bean/signalement';
import {AccueilService} from '../accueil.service';
import {FormControl, FormGroup} from '@angular/forms';
import {SignalementForm} from '../formBean/signalementForm';
import {User} from '../../authentifiation/bean/user';
import {Localisation} from '../Bean/localisation';
import {Quartier} from '../Bean/quartier';

@Component ({
  selector: 'app-signaler',
  templateUrl: './signaler.component.html',
  styleUrls: ['../accueil.component.css'],
  providers: [SignalerService]
})
export class SignalerComponent implements OnInit {
  imageSrc: string = 'assets/images/noimage.jpg';
  geolocationPosition: Position;
  lat: number = 50.6310622 ;
  lng: number = 3.0120553;

  constructor(private accueilService: AccueilService, private signalerService: SignalerService) {}

  signalements: Signalement[];
  signalerForm: FormGroup;
  formulaire = new SignalementForm('', null, '');
  utilisateur: User;
  localisation: Localisation;
  quartier: Quartier;
  proprietaire: any;
  isLog: boolean;

  ngOnInit(): void {
    if (sessionStorage.getItem('utilisateur') != null) {
      this.isLog = true;
    } else {
      this.isLog = false;
    }
    this.afficheSignalement();
    this.signalerForm = new FormGroup({
      'description': new FormControl(this.formulaire.description),
      'indiceDeProprete': new FormControl(this.formulaire.indiceDeProprete),
      'image': new FormControl(this.formulaire.image)
    });

    if (window.navigator && window.navigator.geolocation) {
      window.navigator.geolocation.getCurrentPosition(
        position => {
          this.geolocationPosition = position;
          this.lat = position.coords.latitude;
          this.lng = position.coords.longitude;
          this.localisation = new Localisation(this.lat, this.lng);
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


  signaler(): void {
    console.log('indice: ' + this.formulaire.indiceDeProprete);
    this.quartier = new Quartier(2, 'Autres');
    this.proprietaire = JSON.parse(sessionStorage.getItem('utilisateur'));
    this.utilisateur = new User(this.proprietaire.id, this.proprietaire.nom, this.proprietaire.prenom, this.proprietaire.email);
    this.signalerService.signaler(this.quartier, this.formulaire.description, this.imageSrc, this.localisation, this.formulaire.indiceDeProprete, this.utilisateur )
      .subscribe(data => {
        console.log(data);
      }, error => {
        console.log(error);
      });
  }

  get description() { return this.signalerForm.get('description'); }
  get indiceDeProprete() { return this.signalerForm.get('indiceDeProprete'); }
  get image() { return this.signalerForm.get('image'); }

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
