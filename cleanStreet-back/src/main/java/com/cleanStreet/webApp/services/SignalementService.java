package com.cleanStreet.webApp.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleanStreet.webApp.api.ClarifaiApi;
import com.cleanStreet.webApp.dao.IQuartierDAO;
import com.cleanStreet.webApp.dao.ISignalementDAO;
import com.cleanStreet.webApp.entite.FiltreForm;
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
		// test if signalement can decode and copy the image
		//Decode Base64 and create the File
		try(FileOutputStream stream = new FileOutputStream("/var/lib/jenkins/workspace/Build_Cleanstreet/cleanStreet-front/src/" +signalement.getPhoto())) {
			
			String imageData = signalement.getPhotoBase64().replaceFirst("^data:image/[^;]*;base64,?","");
				byte[] img = Base64.decodeBase64(imageData);
				stream.write(img);
			} catch (IOException e){
				System.out.println("Error : IOexception" + e.getMessage());
			}
			String imageUrl ="/var/lib/jenkins/workspace/Build_Cleanstreet/cleanStreet-front/src/" + signalement.getPhoto();
			
		List<String> resultList = ClarifaiApi.recognize(imageUrl);
		if (resultList.contains("marijuana") || resultList.contains("human") || resultList.contains("nude")
				|| resultList.contains("weapon") || resultList.contains("gore") || resultList.contains("drug")) {
			System.out.println(true);
		} else {
			System.out.println(false);
			signalement.setPhotoBase64("true"); //not to upload a long base64 string
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

	@Override
	public List<Signalement> getSignalementsFiltres(FiltreForm filtreForm) {
		List<Signalement> signalementsFiltres = new ArrayList<Signalement>();
		List<Signalement> allSignalements = getSignalement();
		System.out.println("Lat: " + filtreForm.getLat());
		System.out.println("Lng: " + filtreForm.getLng());
		for (int i = 0; i < allSignalements.size(); i++) {
			if (filtreForm.getQuartier().equals("Tous") && inPerimetre(filtreForm.getPerimetre(), filtreForm.getLat(), filtreForm.getLng(), allSignalements.get(i).getLocalisation().getLatitude(),
					allSignalements.get(i).getLocalisation().getLongitude())) {
				if (inPerimetre(filtreForm.getPerimetre(), filtreForm.getLat(), filtreForm.getLng(), allSignalements.get(i).getLocalisation().getLatitude(),
						allSignalements.get(i).getLocalisation().getLongitude())) {
					signalementsFiltres.add(allSignalements.get(i));
				}
			} else {
				if (allSignalements.get(i).getQuartier().getNom().equals(filtreForm.getQuartier())
						&& inPerimetre(filtreForm.getPerimetre(), filtreForm.getLat(), filtreForm.getLng(), allSignalements.get(i).getLocalisation().getLatitude(),
								allSignalements.get(i).getLocalisation().getLongitude())) {
					signalementsFiltres.add(allSignalements.get(i));
				}
			}
		}

		return signalementsFiltres;
	}

	public boolean inPerimetre(int perimetre, double lat1, double lng1, double lat2, double lng2) {
		int rayon = perimetre * 1000;
		if (DistanceTo(lat1, lng1, lat2, lng2) < rayon) {
			return true;
		}
		return false;
	}



	static double DistanceTo(double lat1, double lon1, double lat2, double lon2)
	{
	    double rlat1 = Math.PI * lat1/180;
	    double rlat2 = Math.PI * lat2/180;
	 
	    double theta = lon1-lon2;
	    double rtheta = Math.PI * theta/180;
	 
	    double dist = Math.sin(rlat1) * Math.sin(rlat2) + Math.cos(rlat1) * Math.cos(rlat2) * Math.cos(rtheta);
	    dist = Math.acos(dist);
	    dist = dist * 180/Math.PI;
	    dist = dist * 60 * 1.1515;
	    System.out.println(dist * 1.609344 * 1000);
	    return dist * 1.609344 * 1000;
	}
	
}
