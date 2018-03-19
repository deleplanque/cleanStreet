package com.cleanStreet.webApp.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
	public String ajouterSignalement(Signalement signalement) {
		// You can change the Image URL accordingly.
		// test if signalement can decode and copy the image
		//Decode Base64 and create the File
		List<Signalement> allSignalements = getSignalement();
		for (int i = 0; i < allSignalements.size(); i++) {
			if (inPerimetre(15, signalement.getLocalisation().getLatitude(), signalement.getLocalisation().getLongitude(), 
					allSignalements.get(i).getLocalisation().getLatitude(), allSignalements.get(i).getLocalisation().getLongitude())) {
				return "exist";
			}
		}
		
		try(FileOutputStream stream = new FileOutputStream("../../cleanStreet-front/src/" +signalement.getPhoto())) {
			String imageData = signalement.getPhotoBase64().replaceFirst("^data:image/[^;]*;base64,?","");
				byte[] img = Base64.decodeBase64(imageData);
				stream.write(img);
			} catch (IOException e){
				System.out.println("Error : IOexception" + e.getMessage());
				signalement.setPhotoBase64("");
				signalement.setPhoto("assets/images/noimage.jpg");
				signalementDAO.saveAndFlush(signalement);
				return "created";
			}

			String imageUrl ="../../cleanStreet-front/src/" + signalement.getPhoto();

		try{
				File file =new File("../../cleanStreet-front/src/assets/mylist.txt");
				FileWriter fw = new FileWriter(file,true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(signalement.getPhoto());
				bw.close();
			  }catch(IOException ioe){
				 System.out.println("Exception occurred:" + ioe.getMessage());
			   }


		List<String> resultList = ClarifaiApi.recognize(imageUrl);
		if (resultList.contains("marijuana") || resultList.contains("human") || resultList.contains("nude")
				|| resultList.contains("weapon") || resultList.contains("gore") || resultList.contains("drug")) {
			System.out.println(true);
			return "picture";
		} else {
			System.out.println(false);
			signalement.setPhotoBase64("true"); //not to upload a long base64 string
			signalementDAO.saveAndFlush(signalement);
			return "created";
		}

	}

	
	/*@Override
	public Signalement ajouterSignalement(Signalement signalement) {
		// You can change the Image URL accordingly.
		// test if signalement can decode and copy the image
		//Decode Base64 and create the File
		try(FileOutputStream stream = new FileOutputStream("../../cleanStreet-front/src/" +signalement.getPhoto())) {
			String imageData = signalement.getPhotoBase64().replaceFirst("^data:image/[^;]*;base64,?","");
				byte[] img = Base64.decodeBase64(imageData);
				stream.write(img);
			} catch (IOException e){
				System.out.println("Error : IOexception" + e.getMessage());
			}
			String imageUrl ="../../cleanStreet-front/src/" + signalement.getPhoto();

		try{
				File file =new File("../../cleanStreet-front/src/assets/mylist.txt");
				FileWriter fw = new FileWriter(file,true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(signalement.getPhoto());
				bw.close();
			  }catch(IOException ioe){
				 System.out.println("Exception occurred:" + ioe.getMessage());
			   }


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

	}*/
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
		filtreForm.setPerimetre(filtreForm.getPerimetre()*1000);
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
		if (DistanceTo(lat1, lng1, lat2, lng2) < perimetre) {
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
	    if (dist > 1) {
	    	dist= 1;
	    }
	    dist = Math.acos(dist);
	    dist = dist * 180/Math.PI;
	    dist = dist * 60 * 1.1515;
	    System.out.println("Distance : " + dist);
	    System.out.println("Distance : " + dist);
	    System.out.println("Distance : " + dist);
	    System.out.println("Distance : " + dist);
	    System.out.println("Distance : " + dist);
	    System.out.println("Distance : " + dist);
	    return dist * 1.609344 * 1000;
	}

	@Override
	public List<Signalement> supprimerSignalement(Signalement signalament) {
		signalementDAO.delete(signalementDAO.findById(signalament.getId()));
		return signalementDAO.findAll();
	}

	@Override
	public Quartier getQuartierParNom(String nom) {
		return quartierDAO.findByNom(nom);
	}
	
}
