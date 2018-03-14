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
import {Router} from '@angular/router';
import {Toast, ToasterService} from 'angular2-toaster';
declare var $: any;
@Component ({
  selector: 'app-signaler',
  templateUrl: './signaler.component.html',
  styleUrls: ['./signaler.component.css'],
  providers: [SignalerService, ToasterService]
})
export class SignalerComponent implements OnInit {
  photoBase64: string = '';
  imageSrc: string = 'assets/images/noimage.jpg';
  geolocationPosition: Position;
  lat: number;
  lng: number;

  constructor(private router: Router, private accueilService: AccueilService,
              private toasterService: ToasterService, private signalerService: SignalerService) {}

  signalements: Signalement[];
  signalerForm: FormGroup;
  formulaire = new SignalementForm('', null, '','');
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
      this.router.navigate(['/accueil']);
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
          console.log('position : ' + this.geolocationPosition);
          this.lat = position.coords.latitude;
          this.lng = position.coords.longitude;
          this.localisation = new Localisation(this.lat, this.lng);
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

  changeListener($event) : void {
        this.readThis($event.target);
      }
      
      readThis(inputValue: any): void {
        var file:File = inputValue.files[0];
        var myReader:FileReader = new FileReader();
      
        myReader.onloadend = (e) => {
          this.photoBase64 = myReader.result;
        }
        myReader.readAsDataURL(file);
      }

  signaler(): void {

    let formIsValid = true;
    console.log(this.formulaire.description);
    if (this.formulaire.description === '') {
      console.log(this.formulaire.description)
      formIsValid = false;
      $('#errorDescription').removeClass('cache');
      $('#description').addClass('invalid');
    } else {
      $('#errorDescription').addClass('cache');
      $('#description').removeClass('invalid');
    }

    console.log('indice: ' + this.formulaire.indiceDeProprete);
    if (this.formulaire.indiceDeProprete === null) {
      formIsValid = false;
      $('#errorIndice').removeClass('cache');
      $('#indice').addClass('invalid');
    } else {
      $('#errorIndice').addClass('cache');
      $('#indice').removeClass('invalid');
    }

    if (this.lat == null || this.lng == null) {
      formIsValid = false;
      this.popToast('error', 'Autorisation', 'Pour accéder à la fonction de ' +
        'signalement, CleanStreet a besoin de votre position, veuillez modifier les paramètres de votre navigateur');
    }

    if (formIsValid) {
      console.log('indice: ' + this.formulaire.indiceDeProprete);
      this.quartier = new Quartier(2, 'Autres');
      this.proprietaire = JSON.parse(sessionStorage.getItem('utilisateur'));
      this.utilisateur = new User(this.proprietaire.id, this.proprietaire.nom, this.proprietaire.prenom, this.proprietaire.email);
      this.imageSrc = "assets/images/"+$('#imageSignalement').val().split('/').pop().split('\\').pop();
      this.signalerService.signaler(this.quartier, this.formulaire.description, this.imageSrc, this.photoBase64, this.localisation,
        this.formulaire.indiceDeProprete, this.utilisateur )
        .subscribe(data => {
          console.log(data);
          this.popToast('success', 'Signalement', 'Votre signalement à bien été pris en compte \n CleanStreet vous remercie !');
        }, error => {
          console.log(error);
          this.popToast('error', 'Signalement', 'Erreur lors de la création de votre signalement. ' +
            'L\'image renseignée ne correspond pas à des dechets');
        });
    }

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
