package com.cleanStreet.webApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleanStreet.webApp.dao.IAuthentificationDAO;
import com.cleanStreet.webApp.entite.ConnexionForm;
import com.cleanStreet.webApp.entite.Utilisateur;

@Service
public class AuthentificationService implements IAuthentificationService {

	@Autowired
	IAuthentificationDAO authentificationDAO;

	private final static String VALIDE_IDENTITE_REGEXP = "^([a-zA-Z]{1,30})$";
	public static final String VALIDE_EMAIL_ADRESSE_REGEXP = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]{2,}.[a-z]{2,4}$";
	private final static String VALIDE_MDP_REGEXP = "^.{6,30}$";

	public Utilisateur inscription(Utilisateur u) {
		Utilisateur utilisateur = authentificationDAO.findByEmail(u.getEmail());
		if (utilisateur != null) {
			return null;
		} else if (coordonneesValides(u)) {
			authentificationDAO.save(u);
			return authentificationDAO.findByEmail(u.getEmail());
		} else
			return null;
	}

	public Utilisateur connexion(ConnexionForm connexionForm){
		Utilisateur utilisateur = authentificationDAO.findByEmail(connexionForm.getEmail());
		if (utilisateur.getMotDePasse().equals(connexionForm.getMotDePasse())){
			System.out.println(utilisateur.getEmail());
			return utilisateur;
		}
		else
			return null;
	}

	public boolean connexion(String email, String mdp) {
		Utilisateur u = authentificationDAO.findByEmail(email);
		if (u != null) {
			if (u.getMotDePasse().equals(mdp)) {
				return true;
			} else
				return false;
		} else
			return false;
	}

	public boolean coordonneesValides(Utilisateur u) {
		if (controleNom(u.getNom()) && controlePrenom(u.getPrenom()) && controleMail(u.getEmail())
				&& controleMdp(u.getMotDePasse())) {
			return true;
		} else
			return false;
	}

	public boolean controleNom(String nom) {
		if (nom.matches(VALIDE_IDENTITE_REGEXP)) {
			return true;
		} else
			return false;
	}

	public boolean controleMdp(String motDePasse) {
		if (motDePasse.matches(VALIDE_MDP_REGEXP)) {
			return true;
		} else
			return false;
	}

	public boolean controleMail(String email) {
		if (email.matches(VALIDE_EMAIL_ADRESSE_REGEXP)) {
			return true;
		} else
			return false;
	}

	public boolean controlePrenom(String prenom) {
		if (prenom.matches(VALIDE_IDENTITE_REGEXP)) {
			return true;
		} else
			return false;
	}

}
