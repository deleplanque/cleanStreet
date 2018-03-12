import {Component, OnInit} from '@angular/core';
import {InscriptionService} from './inscription.service';
import {ConnexionService} from '../connexion/connexion.service';
import {Router} from '@angular/router';
import {UserInscription} from '../bean/userInscription';
import {FormControl, FormGroup, NgForm, Validators} from '@angular/forms';
import {ConnexionFormComponent} from '../connexion/connexion-form-component';
import {AlertService} from '../../alert/_services/alert.service';
declare var $: any;

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription-form.component.html',
  styleUrls: ['../inscription-form.component.css'],
  providers: [InscriptionService, ConnexionService]
})

export class InscriptionFormComponent implements OnInit {


  isValidFormSubmitted = false;
  inscriptionForm: FormGroup;
  formulaire = new UserInscription('', '', '', '', '');


  constructor(private inscriptionService: InscriptionService,  private router: Router, private alertService: AlertService) {}

  ngOnInit(): void {
    this.inscriptionForm = new FormGroup({
      'UInom': new FormControl(this.formulaire.UInom, [Validators.minLength(1)]),
      'UIprenom': new FormControl(this.formulaire.UIprenom, [Validators.minLength(1)]),
      'UImail': new FormControl(this.formulaire.UImail, [Validators.minLength(3)]),
      'UIpass': new FormControl(this.formulaire.UIpass, [Validators.minLength(3)]),
      'UIrepetepass': new FormControl(this.formulaire.UIrepetepass, [Validators.minLength(3)])
    });
  }

  inscription(): void {
    this.inscriptionService.inscription(this.formulaire.UInom, this.formulaire.UIprenom, this.formulaire.UImail, this.formulaire.UIpass)
      .subscribe(data => {
        this.alertService.success('Votre compte à bien été enregistré', true);
        this.router.navigate(['/connexion']);
      }, error => {
        this.alertService.error('Erreur lors de l\'inscription', false);
      });
  }

  get UInom() { return this.inscriptionForm.get('UInom'); }
  get UIprenom() { return this.inscriptionForm.get('UIprenom'); }
  get UImail() { return this.inscriptionForm.get('UImail'); }
  get UIpass() { return this.inscriptionForm.get('UIpass'); }
  get UIrepetepass() { return this.inscriptionForm.get('UIrepetepass'); }


}
