package com.cleanStreet.webApp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cleanStreet.webApp.entite.Utilisateur;
import com.cleanStreet.webApp.services.AuthentificationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebAppApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testServiceAuthentification(){
		Utilisateur u = new Utilisateur(35,"test","alfred","test@hotmail.fr","testtest");
		AuthentificationService as = new AuthentificationService();
		as.inscription(u);
		assertTrue(as.connexion("test@hotmail.fr","testtest"));
	}
}
