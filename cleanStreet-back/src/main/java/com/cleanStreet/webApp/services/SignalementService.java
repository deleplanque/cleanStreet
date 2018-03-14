package com.cleanStreet.webApp.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.cleanStreet.webApp.api.ClarifaiApi;
import com.cleanStreet.webApp.dao.IQuartierDAO;
import com.cleanStreet.webApp.dao.ISignalementDAO;
import com.cleanStreet.webApp.entite.Quartier;
import com.cleanStreet.webApp.entite.Signalement;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignalementService implements ISignalementService{

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
		// CHANGE THE  FILEOUTPUTSTREAM 
		//URL FOR THE PRODUCTION TO : "/home/ubuntu/pepit/cleanStreet/cleanStreet-front/src/"
		try(FileOutputStream stream = new FileOutputStream("C:/Users/Lucas/Desktop/M2S2/PEPIT/cleanStreet-master/cleanStreet-front/src/" +signalement.getPhoto())) {
			
		String imageData = signalement.getPhotoBase64().replaceFirst("^data:image/[^;]*;base64,?","");
			byte[] img = Base64.decodeBase64(imageData);
			stream.write(img);
		} catch (IOException e){
			System.out.println("Error : IOexception" + e.getMessage());
		}
		String imageUrl ="../../cleanStreet-front/src/" + signalement.getPhoto();
		System.out.println(imageUrl);
				// List of Recognized Result from Image
				List<String> resultList = ClarifaiApi.recognize(imageUrl);
				if(resultList.contains("marijuana") || resultList.contains("human") || resultList.contains("nude") 
						|| resultList.contains("weapon") || resultList.contains("gore") || resultList.contains("drug")){
					System.out.println(true);
				}else{
					System.out.println(false);
					signalement.setPhotoBase64("true");
					return signalementDAO.saveAndFlush(signalement);
				}
				// Iteration of Result
				for(String result : resultList) {
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

}
