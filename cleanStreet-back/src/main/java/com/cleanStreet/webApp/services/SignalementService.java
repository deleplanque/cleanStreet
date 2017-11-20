package com.cleanStreet.webApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleanStreet.webApp.dao.ISignalementDAO;
import com.cleanStreet.webApp.entite.Signalement;

@Service
public class SignalementService implements ISignalementService{

	@Autowired
	ISignalementDAO signalementDAO;

	@Override
	public Signalement ajouterSignalement(Signalement signalement) {
		return signalementDAO.save(signalement);
	}

	@Override
	public List<Signalement> getSignalement() {
		return signalementDAO.findAll();
	}

	@Override
	public List<Signalement> getSignalementParQuartier(String quartier) {
		// TODO Auto-generated method stub
		return signalementDAO.findByQuartier(quartier);
	}

}
