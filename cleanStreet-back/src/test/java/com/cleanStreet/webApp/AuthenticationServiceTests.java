package com.cleanStreet.webApp;

import static org.junit.Assert.*;

import java.security.Provider.Service;

import org.junit.Before;
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
		
		@Before
		public void init(){
			
			
		}
		
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
		
		@Test
		public void inscriptionTest(){
			Utilisateur cf = new Utilisateur();
			cf.setEmail("d@d.net");
			cf.setMotDePasse("azerty");
			cf.setId(65);
			cf.setNom("rrefr");
			cf.setPrenom("trgrtef");
			authService.inscription(cf);
			assertTrue(authService.connexion(cf.getEmail(), cf.getMotDePasse()));

		}
		
		@Test
		public void controlesTests(){
			Utilisateur cf = new Utilisateur();
			cf.setEmail("d@d.net");
			cf.setMotDePasse("azerty");
			cf.setId(65);
			cf.setNom("rrefr");
			cf.setPrenom("trgrtef");
			
			Utilisateur cf1 = new Utilisateur();
			cf1.setEmail("d@net");
			cf1.setMotDePasse("aerty");
			cf1.setId(65);
			cf1.setNom("rre5fr");
			cf1.setPrenom("trgr5tef");
			
			assertTrue(authService.coordonneesValides(cf));
			assertFalse(authService.coordonneesValides(cf1));
			assertFalse(authService.controleMail(cf1.getEmail()));
			assertFalse(authService.controlePrenom(cf1.getPrenom()));
			assertFalse(authService.controleMdp(cf1.getMotDePasse()));
		}
}
