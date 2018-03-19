package com.cleanStreet.webApp.contolleurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cleanStreet.webApp.entite.ConnexionForm;
import com.cleanStreet.webApp.entite.FiltreForm;
import com.cleanStreet.webApp.entite.Quartier;
import com.cleanStreet.webApp.entite.ResponseCreation;
import com.cleanStreet.webApp.entite.Signalement;
import com.cleanStreet.webApp.services.ISignalementService;

@Controller
@RequestMapping("/api")
public class SignalementControlleur {

	@Autowired
	ISignalementService signalementService;

	@RequestMapping(value = "/ajouterSignalement", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseCreation> ajouterSignalement(@RequestBody Signalement signalement) {
		String s = signalementService.ajouterSignalement(signalement);
		return new ResponseEntity<ResponseCreation>(new ResponseCreation(s), HttpStatus.OK);
	}

	@RequestMapping(value = "/afficheSignalement", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Signalement>> getSignalement() {
		return new ResponseEntity<List<Signalement>>(signalementService.getSignalement(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getQuartiers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Quartier>> getQuartiers() {
		return new ResponseEntity<List<Quartier>>(signalementService.getQuartiers(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getSignalementById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Signalement> getSignalementById(@PathVariable("id") int id) {
		return new ResponseEntity<Signalement>(signalementService.getSignalementById(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/afficheSignalementParQuartier/{quartier}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Signalement>> getSignalementParQuartier(@PathVariable("quartier") String quartier) {
		return new ResponseEntity<List<Signalement>>(signalementService.getSignalementParQuartier(quartier),
				HttpStatus.OK);

	}

	@RequestMapping(value = "/supprimerSignalement", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> supprimerSignalement(@RequestParam(value = "id") int id) {
		signalementService.supprimerSignalement(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/afficheSignalementParIdUtilisateur", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Signalement>> afficheSignalementParIdUtilisateur(@RequestParam(value = "id") int id) {
		return new ResponseEntity<List<Signalement>>(signalementService.afficheSignalementParIdUtilisateur(id),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/getSignalementsFiltres", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Signalement>> getSignalementsFiltres(@RequestBody FiltreForm filtreForm) {
		return new ResponseEntity<List<Signalement>>(signalementService.getSignalementsFiltres(filtreForm), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/supprimerSignalement", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Signalement>> supprimerSignalement(@RequestBody Signalement signalament) {
		return new ResponseEntity<List<Signalement>>(signalementService.supprimerSignalement(signalament), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getQuartierParNom/{nom}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Quartier> getQuartierParNom(@PathVariable(value = "nom") String nom) {
		return new ResponseEntity<Quartier>(signalementService.getQuartierParNom(nom),
				HttpStatus.OK);

	}
}
