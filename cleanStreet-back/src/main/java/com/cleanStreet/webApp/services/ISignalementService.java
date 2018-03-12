package com.cleanStreet.webApp.services;

import java.util.List;

import com.cleanStreet.webApp.entite.Signalement;

public interface ISignalementService {

	Signalement ajouterSignalement(Signalement signalement);
	List<Signalement> getSignalement();
	List<Signalement> getSignalementParQuartier(String quartier);
	void supprimerSignalement(int id);
	List<Signalement> afficheSignalementParIdUtilisateur(int id);
	Signalement getSignalementById(int id);

}
