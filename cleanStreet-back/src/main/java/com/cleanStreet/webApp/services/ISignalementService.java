package com.cleanStreet.webApp.services;

import java.util.List;

import com.cleanStreet.webApp.entite.FiltreForm;
import com.cleanStreet.webApp.entite.Quartier;
import com.cleanStreet.webApp.entite.Signalement;

public interface ISignalementService {

	String ajouterSignalement(Signalement signalement);
	List<Signalement> getSignalement();
	List<Signalement> getSignalementParQuartier(String quartier);
	void supprimerSignalement(int id);
	List<Signalement> afficheSignalementParIdUtilisateur(int id);
	Signalement getSignalementById(int id);
	List<Quartier> getQuartiers();
	List<Signalement> getSignalementsFiltres(FiltreForm filtreForm);
	List<Signalement> supprimerSignalement(Signalement signalament);
	Quartier getQuartierParNom(String nom);

}
