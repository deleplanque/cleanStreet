
import {Component, OnInit} from '@angular/core';
import {UserConnexion} from '../bean/userConnexion';
import {FormControl, FormGroup, NgForm, Validators} from '@angular/forms';
import {ConnexionService} from './connexion.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion-form.component.html',
  styleUrls: ['../inscription-form.component.css'],
  providers: [ConnexionService]
})

export class ConnexionFormComponent implements OnInit {

  connexionForm: FormGroup;
  formulaire = new UserConnexion('', '');

  constructor(private connexionService: ConnexionService, private router: Router) {}

  ngOnInit(): void {
    this.connexionForm = new FormGroup({
      'UCmail': new FormControl(this.formulaire.UCemail, [Validators.minLength(1)]),
      'UCpass': new FormControl(this.formulaire.UCpass, [Validators.minLength(1)])
    });
  }

  connexion(): void {
    this.connexionService.connexion(this.formulaire.UCemail, this.formulaire.UCpass)
      .subscribe(data => {
<<<<<<< HEAD
        this.router.navigate(['accueil']);
=======
        console.log('data', data);
        this.router.navigate(['/accueil']);
>>>>>>> 0000004fonc-gestionSignalement
      }, error => {
        console.log(error);
      });
  }


  get UCmail() { return this.connexionForm.get('UCmail'); }
  get UCpass() { return this.connexionForm.get('UCpass'); }

}
