package com.cleanStreet.webApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleanStreet.webApp.dao.IContactDAO;
import com.cleanStreet.webApp.entite.Contact;

@Service
public class ContactService implements IContactService{

	@Autowired
	IContactDAO contactDAO;


	@Override
	public Contact creerContact(Contact contact) {
		contactDAO.saveAndFlush(contact);
		return contact;
	}

	@Override
	public List<Contact> getContact() {
		return contactDAO.findAll();
	}

}
