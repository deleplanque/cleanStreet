import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AccueilComponent} from './accueil/accueil.component';
import {InscriptionFormComponent} from './authentifiation/inscription/inscription-form.component';
import {ConnexionFormComponent} from './authentifiation/connexion/connexion-form-component';
import {SignalerComponent} from './accueil/signaler/signaler.component';

const routes: Routes = [
  { path: '', redirectTo: '/inscription', pathMatch: 'full' },
  { path: 'inscription', component: InscriptionFormComponent},
  { path: 'accueil', component: AccueilComponent },
  { path: 'connexion', component: ConnexionFormComponent},
  { path: 'signalez', component: SignalerComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes)],
  exports: [ RouterModule ]
})
export  class  AppRoutingModule {}
