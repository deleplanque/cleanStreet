
import {Component} from '@angular/core';
import {UserConnexion} from '../bean/userConnexion';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion-form.component.html'
})

export class ConnexionFormComponent {

  model = new UserConnexion('mail', 'pass');

  submitted = false;

  onSubmit() { this.submitted = true; }

  newUserInscription() {
    this.model = new UserConnexion('mail', 'pass');
  }
}
