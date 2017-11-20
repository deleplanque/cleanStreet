import {Component, OnInit} from '@angular/core';
import {InscriptionService} from './inscription.service';
import {ConnexionService} from '../connexion/connexion.service';
import {Router} from '@angular/router';
import {UserInscription} from '../bean/userInscription';
import {FormControl, FormGroup, NgForm, Validators} from '@angular/forms';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription-form.component.html',
  styleUrls: ['../inscription-form.component.css'],
  providers: [InscriptionService, ConnexionService]
})

export class InscriptionFormComponent implements OnInit {



  inscriptionForm: FormGroup;
  formulaire = new UserInscription('', '', '', '', '');


  constructor(private inscriptionService: InscriptionService, private connexionService: ConnexionService, private router: Router) {}

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
        console.log('data', data);
      }, error => {
        console.log(error);
      });
  }

  get UInom() { return this.inscriptionForm.get('UInom'); }
  get UIprenom() { return this.inscriptionForm.get('UIprenom'); }
  get UImail() { return this.inscriptionForm.get('UImail'); }
  get UIpass() { return this.inscriptionForm.get('UIpass'); }
  get UIrepetepass() { return this.inscriptionForm.get('UIrepetepass'); }

}
