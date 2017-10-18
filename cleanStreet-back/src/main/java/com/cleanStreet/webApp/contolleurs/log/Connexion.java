package com.cleanStreet.webApp.contolleurs.log;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Connexion {

	@RequestMapping("/api/connexion")
	String insctiption() {
		return "Connexion";
	}

}
