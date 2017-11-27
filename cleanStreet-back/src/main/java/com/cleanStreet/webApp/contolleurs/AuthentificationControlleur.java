package com.cleanStreet.webApp.contolleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cleanStreet.webApp.entite.ConnexionForm;
import com.cleanStreet.webApp.entite.Utilisateur;
import com.cleanStreet.webApp.services.IAuthentificationService;

@Controller
@RequestMapping("/api")
public class AuthentificationControlleur {

	@Autowired
	IAuthentificationService authentificationService;

	@RequestMapping(value = "/inscription", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Utilisateur> inscription(@RequestBody Utilisateur utilisateur) {

		Utilisateur u = authentificationService.inscription(utilisateur);
		if (u == null) {
			return new ResponseEntity<Utilisateur>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Utilisateur>(new Utilisateur(u.getId(), u.getNom(), u.getPrenom(), u.getEmail(), u.getSignalements()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/connexion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Utilisateur> connexion(@RequestBody ConnexionForm connexionForm) {

		Utilisateur u = authentificationService.connexion(connexionForm);
		if (u == null) {
			return new ResponseEntity<Utilisateur>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Utilisateur>(new Utilisateur(u.getId(), u.getNom(), u.getPrenom(), u.getEmail(),u.getSignalements()), HttpStatus.OK);
	}

}
