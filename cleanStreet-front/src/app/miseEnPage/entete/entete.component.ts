import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component ({
  selector: 'app-entete',
  templateUrl: 'entete.component.html'
})

export class EnteteComponent {

  constructor( private router: Router) {}

  deconnexion(): void {
    sessionStorage.clear();
    this.router.navigate(['/connexion']);
  }
}
