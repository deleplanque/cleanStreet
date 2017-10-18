package com.cleanStreet.webApp.contolleurs.log;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Inscription {

	@RequestMapping("/api/inscription")
	String insctiption() {
		System.out.println("je passe dans la methode inscription");
		return "inscription";
	}
}
