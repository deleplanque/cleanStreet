package com.cleanStreet.webApp.contolleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cleanStreet.webApp.entite.Localisation;
import com.cleanStreet.webApp.services.ILocalisationService;

@Controller
@RequestMapping("/api")
public class LocalisationControlleur {


	@Autowired
	ILocalisationService localisationService;

	@RequestMapping(value="/afficheLocalisation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Localisation>> getLocalisation(){
		return new ResponseEntity<List<Localisation>>(localisationService.getSignalement(), HttpStatus.OK);
	}

	@RequestMapping(value="/localisation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Localisation> getLocalisationParLatitudeEtLongitude(@RequestParam (value="latitude") double latitude, @RequestParam (value="longitude") double longitude){
		return new ResponseEntity<Localisation>(localisationService.findByLatitudeAndLongitude(latitude, longitude), HttpStatus.OK);
	}


	@RequestMapping(value = "/creerLocalisation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Localisation> creerLocalisation(@RequestBody Localisation localisation) {
		localisation = localisationService.creerLocalisation(localisation);
		return new ResponseEntity<Localisation>(localisation, HttpStatus.OK);
	}


}
