import {Component, OnInit} from '@angular/core';
import {LocationStrategy, PathLocationStrategy} from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
 // providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}]
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
