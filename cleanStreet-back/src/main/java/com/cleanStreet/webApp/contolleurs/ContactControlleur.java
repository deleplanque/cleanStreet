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

import com.cleanStreet.webApp.entite.Contact;
import com.cleanStreet.webApp.services.IContactService;

@Controller
@RequestMapping("/api")
public class ContactControlleur {

	@Autowired
	IContactService contactService;

	@RequestMapping(value = "/ajouterContact", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contact> ajouterContact(@RequestBody Contact contact) {

		Contact c = contactService.ajouterContact(contact);
		if (c == null) {
			return new ResponseEntity<Contact>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Contact>(s, HttpStatus.CREATED);
	}

	@RequestMapping(value="/afficheContact", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Contact>> getContact(){
		return new ResponseEntity<List<Contact>>(contactService.getContact(), HttpStatus.OK);
	}

}