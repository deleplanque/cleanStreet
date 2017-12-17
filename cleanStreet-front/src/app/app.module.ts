import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {InscriptionFormComponent} from './authentifiation/inscription/inscription-form.component';
import {ConnexionFormComponent} from './authentifiation/connexion/connexion-form-component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app.routing.module';
import {AgmCoreModule} from '@agm/core';
import {AlertComponent} from './alert/_directives/alert.component';
import {InscriptionService} from './authentifiation/inscription/inscription.service';
import {ConnexionService} from './authentifiation/connexion/connexion.service';
import {AlertService} from './alert/_services/alert.service';
import {AccueilService} from './accueil/accueil.service';
import {SignalerService} from './accueil/signaler/signaler.service';
import {OptimiserService} from './optimiser/optimiser.service';
import {SignalerComponent} from './accueil/signaler/signaler.component';
import {AccueilComponent} from './accueil/accueil.component';
import {OptimiserComponent} from './optimiser/optimiser.component';
import {EnteteComponent} from './miseEnPage/entete/entete.component';
import {PiedDePageComponent} from './miseEnPage/piedDePage/piedDePage.component';
import { HashLocationStrategy } from '@angular/common';


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
    EnteteComponent,
    PiedDePageComponent
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
    AlertService],
  bootstrap: [AppComponent],
})
export class AppModule { }
