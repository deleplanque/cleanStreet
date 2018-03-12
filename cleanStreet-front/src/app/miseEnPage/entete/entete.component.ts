import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {ConnexionService} from '../../authentifiation/connexion/connexion.service';
import {InscriptionService} from '../../authentifiation/inscription/inscription.service';

declare var $: any;

@Component ({
  selector: 'app-entete',
  templateUrl: 'entete.component.html',
  providers: [ConnexionService, InscriptionService]
})

export class EnteteComponent implements OnInit {

  constructor( private router: Router, private connexionService: ConnexionService, private inscriptionService: InscriptionService) {}

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
        $('#modalConnexion').modal('close');
      }, error => {
          console.log('erreur authent');
      });
  }

  sinscrire(): void {



    var passwordInput = $('#passwordI');
      var passwordLength = this.passwordI.length;
      console.log(passwordLength);
      var errorDisplay = (passwordLength < 6)?'inline':'none';
      console.log(errorDisplay);
      $('.error').css('display', errorDisplay);

    if (this.passwordI === this.passwordIR) {
      this.inscriptionService.inscription(this.nomI, this.prenomI, this.emailI, this.passwordI)
        .subscribe(data => {
          sessionStorage.setItem('utilisateur', JSON.stringify(data));
          this.isLog = true;
          $('#modalInscription').modal('close');
          this.router.navigate(['/accueil']);
        }, error => {
          console.log('erreur authent');
        });
    } else {
      console.log('inscription failed');
    }
  }


}
