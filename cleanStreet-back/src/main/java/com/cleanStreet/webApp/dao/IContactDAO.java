package com.cleanStreet.webApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanStreet.webApp.entite.Signalement;

public interface IContactDAO extends JpaRepository<Contact, Long>{


	Contact findById(int id);


}
