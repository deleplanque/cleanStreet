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


const googleMapsCore = AgmCoreModule.forRoot({
  apiKey : 'AIzaSyCPoOajyOD44_Dwie5XRMofsEDKoM7JMrs',
});


@NgModule({
  declarations: [
    AppComponent,
    InscriptionFormComponent,
    ConnexionFormComponent,
    AccueilComponent,
    SignalerComponent
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
    SignalerService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

