import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {InscriptionFormComponent} from './authentifiation/inscription/inscription-form.component';
import {ConnexionFormComponent} from './authentifiation/connexion/connexion-form-component';
import {AccueilComponent} from './accueil/accueil.component';
import {OptimiserComponent} from './optimiser/optimiser.component';
import {SignalerComponent} from './accueil/signaler/signaler.component';
import {ContactComponent} from './contact/contact.component';
import {SensibiliserComponent} from './sensibiliser/sensibiliser.component';

const routes: Routes = [

  { path: '', redirectTo: '/accueil', pathMatch: 'full' },
  { path: 'inscription', component: InscriptionFormComponent},
  { path: 'accueil', component: AccueilComponent },
  { path: 'connexion', component: ConnexionFormComponent},
  { path: 'signalez', component: SignalerComponent },
  { path: 'optimisez', component: OptimiserComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'sensibilisez', component: SensibiliserComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes)],
  exports: [ RouterModule ]
})
export  class  AppRoutingModule {}
