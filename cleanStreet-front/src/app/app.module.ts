import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {InscriptionFormComponent} from './authentifiation/inscription/inscription-form.component';
import {ConnexionFormComponent} from './authentifiation/connexion/connexion-form-component';
import {InscriptionService} from './authentifiation/inscription/inscription.service';
import {HttpModule} from '@angular/http';
import {AppRoutingModule} from './app.routing.module';
import {AccueilComponent} from './accueil/accueil.component';
import {HttpClientModule} from '@angular/common/http';

import {ConnexionService} from './authentifiation/connexion/connexion.service';
import {SignalerComponent} from './accueil/signaler/signaler.component';
import {AccueilService} from './accueil/accueil.service';
import {SignalerService} from './accueil/signaler/signaler.service';

import {AgmCoreModule} from '@agm/core';
import {OptimiserComponent} from './accueil/optimiser/optimiser.component';
import {AlertComponent} from './alert/_directives/alert.component';
import {OptimiserService} from './accueil/optimiser/optimiser.service';
import {AlertService} from './alert/_services/alert.service';


const googleMapsCore = AgmCoreModule.forRoot({
  apiKey : 'AIzaSyCPoOajyOD44_Dwie5XRMofsEDKoM7JMrs',
  libraries: ['places']
});


@NgModule({
  declarations: [
    AppComponent,
    InscriptionFormComponent,
    ConnexionFormComponent,
    AccueilComponent,
    SignalerComponent,
    OptimiserComponent,
    AlertComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    googleMapsCore
  ],
  providers: [
    InscriptionService,
    ConnexionService,
    AccueilService,
    SignalerService,
    OptimiserService,
    AlertService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

