package com.cleanStreet.webApp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cleanStreet.webApp.entite.Localisation;
import com.cleanStreet.webApp.entite.Quartier;
import com.cleanStreet.webApp.services.LocalisationService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalisationServiceTests {
	@Autowired
	LocalisationService locService;
	
	@Test
	public void testCreerLocalisation(){
		Quartier quartier = new Quartier();
		quartier.setId(1);
		quartier.setNom("Fives");
		Localisation loc = new Localisation();
		loc.setId(5);
		loc.setLatitude(50.609050);
		loc.setLongitude(3.1358743);
		loc.setQuartier(quartier);
		locService.creerLocalisation(loc);
		assertEquals(loc.toString(),locService.creerLocalisation(loc).toString());
	}
	
	@Test
	public void testFindByLatitudeAndLongitude(){
		Quartier quartier = new Quartier();
		quartier.setId(1);
		quartier.setNom("Fives");
		Localisation loc = new Localisation();
		loc.setId(5);
		loc.setLatitude(50.609050);
		loc.setLongitude(3.1358743);
		loc.setQuartier(quartier);
		locService.creerLocalisation(loc);
		assertEquals(loc.getId(),locService.findByLatitudeAndLongitude(50.609050, 3.1358743).getId());
	}
	

	
	
}
