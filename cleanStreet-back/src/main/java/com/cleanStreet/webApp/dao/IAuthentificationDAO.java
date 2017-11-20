package com.cleanStreet.webApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanStreet.webApp.entite.Utilisateur;

public interface IAuthentificationDAO extends JpaRepository<Utilisateur, Long>{
	Utilisateur findByEmail(String email);
}
