
import {Component, OnInit} from '@angular/core';
import {UserConnexion} from '../bean/userConnexion';
import {FormControl, FormGroup, NgForm, Validators} from '@angular/forms';
import {ConnexionService} from './connexion.service';
import {Router} from '@angular/router';
import {User} from '../bean/user';
import {AlertService} from '../../alert/_services/alert.service';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion-form.component.html',
  styleUrls: ['../inscription-form.component.css'],
  providers: [ConnexionService]
})

export class ConnexionFormComponent implements OnInit {

  connexionForm: FormGroup;
  formulaire = new UserConnexion('', '');
  utilisateur: User;

  constructor(private connexionService: ConnexionService, private router: Router, private alertService: AlertService) {}

  ngOnInit(): void {
    this.connexionForm = new FormGroup({
      'UCmail': new FormControl(this.formulaire.UCemail, [Validators.minLength(1)]),
      'UCpass': new FormControl(this.formulaire.UCpass, [Validators.minLength(1)])
    });
  }

  connexion(): void {
    this.connexionService.connexion(this.formulaire.UCemail, this.formulaire.UCpass)
      .subscribe(data => {
        sessionStorage.setItem('utilisateur', JSON.stringify(data));
        this.router.navigate(['/accueil']);
      }, error => {
        this.alertService.error('Identifiant incorectes', false);
      });
  }


  get UCmail() { return this.connexionForm.get('UCmail'); }
  get UCpass() { return this.connexionForm.get('UCpass'); }

}
