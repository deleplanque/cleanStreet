package com.cleanStreet.webApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleanStreet.webApp.api.ClarifaiApi;
import com.cleanStreet.webApp.dao.IQuartierDAO;
import com.cleanStreet.webApp.dao.ISignalementDAO;
import com.cleanStreet.webApp.entite.Quartier;
import com.cleanStreet.webApp.entite.Signalement;

@Service
public class SignalementService implements ISignalementService {

	@Autowired
	ISignalementDAO signalementDAO;

	@Autowired
	ILocalisationService localisationService;

	@Autowired
	IQuartierDAO quartierDAO;

	@Override
	public Signalement ajouterSignalement(Signalement signalement) {
		// You can change the Image URL accordingly.
		String imageUrl = "C:/Users/david/Pictures/pistolet.jpg";

		List<String> resultList = ClarifaiApi.recognize(imageUrl);
		if (resultList.contains("marijuana") || resultList.contains("human") || resultList.contains("nude")
				|| resultList.contains("weapon") || resultList.contains("gore") || resultList.contains("drug")) {
			System.out.println(true);
		} else {
			System.out.println(false);
			return signalementDAO.saveAndFlush(signalement);
		}
		for (String result : resultList) {
			System.out.println(result);
		}
		return null;

	}

	@Override
	public List<Signalement> getSignalement() {
		return signalementDAO.findAll();
	}

	@Override
	public List<Signalement> getSignalementParQuartier(String quartier) {
		return signalementDAO.findByQuartier(quartier);
	}

	@Override
	public void supprimerSignalement(int id) {
		Signalement s = signalementDAO.findById(id);
		signalementDAO.delete(s);
	}

	@Override
	public List<Signalement> afficheSignalementParIdUtilisateur(int id) {
		return signalementDAO.findByProprietaireId(id);
	}

	@Override
	public Signalement getSignalementById(int id) {
		return signalementDAO.findById(id);
	}

	@Override
	public List<Quartier> getQuartiers() {
		return quartierDAO.findAll();
	}

	public double deg2rad(double x) {
		return Math.PI * x / 180;
	}

	public double get_distance_m(double lat1, double lng1, double lat2, double lng2) {
		int earth_radius = 6378137;
		double rlo1 = deg2rad(lng1); 
		double rla1 = deg2rad(lat1);
		double rlo2 = deg2rad(lng2);
		double rla2 = deg2rad(lat2);
		double dlo = (rlo2 - rlo1) / 2;
		double dla = (rla2 - rla1) / 2;
		double a = (Math.sin(dla) * Math.sin(dla))
				+ Math.cos(rla1) * Math.cos(rla2) * (Math.sin(dlo) * Math.sin(dlo));
		double d = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return (earth_radius * d);
	}
}
