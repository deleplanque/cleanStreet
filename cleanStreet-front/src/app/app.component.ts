import {Component, OnInit} from '@angular/core';
import {LocationStrategy, PathLocationStrategy} from '@angular/common';
import {Toast, ToasterService} from 'angular2-toaster';
import {AccueilService} from './accueil/accueil.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements  OnInit {

  isLog: boolean;

  ngOnInit(): void {
    if (sessionStorage.getItem('utilisateurs') != null) {
      this.isLog = true;
    } else {
      this.isLog = false;
    }
  }
}
