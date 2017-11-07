package com.cleanStreet.webApp.contolleurs.log;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cleanStreet.webApp.domaine.Utilisateur;

@RestController
@SpringBootApplication
public class Inscription {

	@RequestMapping(value="/api/inscription", method=RequestMethod.POST)
	public ResponseEntity<Void> inscription(@RequestBody Utilisateur utilisateur) {
		System.out.println(utilisateur.getNom());
		return null;
	}
}
