import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

declare var $: any;

@Component ({
  selector: 'app-entete',
  templateUrl: 'entete.component.html'
})

export class EnteteComponent implements OnInit {

  constructor( private router: Router) {}

  isLog: boolean;

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
    this.router.navigate(['/connexion']);
  }
}
