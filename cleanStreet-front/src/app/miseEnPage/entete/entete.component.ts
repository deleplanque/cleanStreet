import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ConnexionService} from '../../authentifiation/connexion/connexion.service';
import {InscriptionService} from '../../authentifiation/inscription/inscription.service';
import {Toast, ToasterService} from 'angular2-toaster';

declare var $: any;

@Component ({
  selector: 'app-entete',
  templateUrl: 'entete.component.html',
  providers: [ConnexionService, InscriptionService, ToasterService]
})

export class EnteteComponent implements OnInit {

  constructor( private router: Router, private connexionService: ConnexionService,
               private inscriptionService: InscriptionService, private toasterService: ToasterService) {}

  isLog: boolean;
  email: string;
  password: string;

  nomI: string;
  prenomI: string;
  emailI: string;
  passwordI: string;
  passwordIR: string;

  ngOnInit(): void {
    $('.button-collapse').sideNav();
    $('.modal').modal();
  }

  connexion(): void {
    $('#modalConnexion').modal('open');
  }

  inscription(): void {
    $('#modalInscription').modal('open');
  }

  deconnexion(): void {
    sessionStorage.clear();
    this.isLog = false;
    this.router.navigate(['/accueil']);
  }

  seConnecter(): void {
    console.log(this.email);
    this.connexionService.connexion(this.email, this.password)
      .subscribe(data => {
        sessionStorage.setItem('utilisateur', JSON.stringify(data));
        this.isLog = true;
        this.router.navigate(['/accueil']);
        console.log(data);
        this.popToast('success', 'Connexion', 'Bonjour ' + data.nom + ' ' + data.prenom);
        $('#modalConnexion').modal('close');
      }, error => {
        this.popToast('error', 'Connexion', 'Mail ou mot de passe incorrect');
      });
  }

  sinscrire(): void {

   let formIsValid = true;

    if (this.nomI == null) {
      formIsValid = false;
      $('#errorNomI').removeClass('cache');
      $('#nomI').addClass('invalid');
    }  else {
      $('#errorNomI').addClass('cache');
      $('#nomI').removeClass('invalid');
    }

    if (this.prenomI == null) {
      formIsValid = false;
      $('#errorPrenomI').removeClass('cache');
      $('#prenomI').addClass('invalid');
    } else {
      $('#errorPrenomI').addClass('cache');
      $('#prenomI').removeClass('invalid');
    }

    if (this.emailI == null) {
      formIsValid = false;
      $('#errorEmailI').removeClass('cache');
      $('#emailI').addClass('invalid');
    }else {
      $('#errorEmailI').addClass('cache');
      $('#emailI').removeClass('invalid');
    }

    if (this.passwordI == null) {
      formIsValid = false;
      $('#errorPasswordI').removeClass('cache');
      $('#passwordI').addClass('invalid');
    } else {
      $('#errorPasswordI').addClass('cache');
      $('#passwordI').removeClass('invalid');
    }

    if (this.passwordI !== this.passwordIR) {
      formIsValid = false;
      $('#errorPasswordIR').removeClass('cache');
      $('#passwordIR').addClass('invalid');
      $('#passwordI').addClass('invalid');
    } else {
      $('#errorPasswordIR').addClass('cache');
      $('#passwordIR').removeClass('invalid');
      $('#passwordI').removeClass('invalid');
    }

    if (formIsValid) {
      this.inscriptionService.inscription(this.nomI, this.prenomI, this.emailI, this.passwordI)
        .subscribe(data => {
          sessionStorage.setItem('utilisateur', JSON.stringify(data));
          this.isLog = true;
          $('#modalInscription').modal('close');
          this.router.navigate(['/accueil']);
          this.popToast('success', 'Inscription', 'Bienvenue ' + data.nom + ' ' + data.prenom);
        }, error => {
          this.popToast('error', 'Inscription', 'Erreur du serveur lors de l\'inscription' );
        });
    } else {
      this.popToast('error', 'Inscription', 'Erreur lors de l\'inscription, veuillez vérifier ' +
        'que tout les champs soient bien renseignés' );
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
