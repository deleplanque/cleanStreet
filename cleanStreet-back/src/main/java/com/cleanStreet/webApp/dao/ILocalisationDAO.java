package com.cleanStreet.webApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleanStreet.webApp.entite.Localisation;



public interface ILocalisationDAO extends JpaRepository<Localisation, Long>{

	Localisation findByLatitudeAndLongitude(double latitude, double longitude);

}
