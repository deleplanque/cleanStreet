package com.cleanStreet.webApp;

import static org.junit.Assert.*;

import java.security.Provider.Service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cleanStreet.webApp.entite.ConnexionForm;
import com.cleanStreet.webApp.entite.Utilisateur;
import com.cleanStreet.webApp.services.AuthentificationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceTests {
		
		@Autowired
		AuthentificationService authService;
		
		@Test
		public void connectionTest(){
			ConnexionForm cf = new ConnexionForm();
			cf.setEmail("d.dylan62138@gmail.com");
			cf.setMotDePasse("azerty");
			Utilisateur u = new Utilisateur();
			u.setId(1);
			u.setNom("deleplanque");
			u.setPrenom("dylan");
			u.setEmail("d.dylan62138@gmail.com");
			u.setMotDePasse("azerty");
			
			assertEquals(u.toString(),authService.connexion(cf).toString());
			
			
		}
}
