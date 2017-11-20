package com.cleanStreet.webApp.services;

import com.cleanStreet.webApp.entite.ConnexionForm;
import com.cleanStreet.webApp.entite.Utilisateur;

public interface IAuthentificationService {

	Utilisateur inscription(Utilisateur u);
	boolean controleNom(String nom);
	boolean controleMdp(String motDePasse);
	boolean controleMail(String email);
	boolean controlePrenom(String prenom);
	boolean coordonneesValides(Utilisateur u);
	Utilisateur connexion(ConnexionForm connexionForm);
}
