package com.cleanStreet.webApp;

import static org.junit.Assert.*;

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
	public void helloTest(){
		WebAppApplication wa = new	WebAppApplication();				
		assertEquals("Bienvenue",wa.acceuil());
	}
}
