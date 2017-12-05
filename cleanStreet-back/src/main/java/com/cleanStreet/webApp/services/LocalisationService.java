package com.cleanStreet.webApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleanStreet.webApp.dao.ILocalisationDAO;
import com.cleanStreet.webApp.entite.Localisation;

@Service
public class LocalisationService implements ILocalisationService{

	@Autowired
	ILocalisationDAO localisationDao;

	@Override
	public Localisation findByLatitudeAndLongitude(double latitude, double longitude) {
		return localisationDao.findByLatitudeAndLongitude(latitude, longitude);
	}

	@Override
	public Localisation creerLocalisation(Localisation localisation) {
		localisationDao.saveAndFlush(localisation);
		return localisation;
	}

	@Override
	public List<Localisation> getSignalement() {
		return localisationDao.findAll();
	}

}
