package com.cleanStreet.webApp.services;

import java.util.List;

import com.cleanStreet.webApp.entite.Localisation;

public interface ILocalisationService {

	Localisation findByLatitudeAndLongitude(double latitude, double longitude);
	Localisation creerLocalisation(Localisation localisation);
	List<Localisation> getSignalement();
}
