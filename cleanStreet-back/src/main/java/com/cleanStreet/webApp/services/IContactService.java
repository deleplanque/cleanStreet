package com.cleanStreet.webApp.services;

import java.util.List;

import com.cleanStreet.webApp.entite.Contact;

public interface IContactService {

    
	Contact creerContact(Contact contact);
	List<Contact> getContact();
}
