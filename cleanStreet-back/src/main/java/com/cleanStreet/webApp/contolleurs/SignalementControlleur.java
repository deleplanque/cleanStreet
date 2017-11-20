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

import com.cleanStreet.webApp.entite.Signalement;
import com.cleanStreet.webApp.services.ISignalementService;

@Controller
@RequestMapping("/api")
public class SignalementControlleur {

	@Autowired
	ISignalementService signalementService;

	@RequestMapping(value = "/ajouterSignalement", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Signalement> inscription(@RequestBody Signalement signalement) {

		Signalement s = signalementService.ajouterSignalement(signalement);
		if (s == null) {
			return new ResponseEntity<Signalement>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Signalement>(s, HttpStatus.CREATED);
	}

	@RequestMapping(value="/afficheSignalement", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Signalement>> getSignalement(){
		return new ResponseEntity<List<Signalement>>(signalementService.getSignalement(), HttpStatus.FOUND);
	}

	@RequestMapping(value="/afficheSignalementParQuartier", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Signalement>> getSignalementParQuartier(@RequestParam(value="quartier") String quartier){
		return new ResponseEntity<List<Signalement>>(signalementService.getSignalementParQuartier(quartier), HttpStatus.FOUND);
	}

}
