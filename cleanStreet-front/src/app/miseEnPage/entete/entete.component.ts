import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component ({
  selector: 'app-entete',
  templateUrl: 'entete.component.html'
})

export class EnteteComponent implements OnInit {

  constructor( private router: Router) {}

  isLog: boolean;

  ngOnInit(): void {
    /*
    if (sessionStorage.getItem('utilisateur') != null) {
      this.isLog = true;
    } else {
      this.isLog = false;
    }*/
  }


  deconnexion(): void {
    sessionStorage.clear();
    this.router.navigate(['/connexion']);
  }
}
