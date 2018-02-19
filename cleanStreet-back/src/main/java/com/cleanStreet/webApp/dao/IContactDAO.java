package com.cleanStreet.webApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanStreet.webApp.entite.Contact;

public interface IContactDAO extends JpaRepository<Contact, Long>{
	Contact findById(int id);
}
