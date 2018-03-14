package com.cleanStreet.webApp.entite;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Signalement implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private Quartier quartier;
	private String description;
	private String photo;
	private String photoBase64;
	@OneToOne(cascade=CascadeType.ALL)
	private Localisation localisation;

	private int indiceDeProprete;

	@ManyToOne
	private Utilisateur proprietaire;

	public Signalement(){}

	public Signalement(Quartier quartier,String description, String photo, String photoBase64, Localisation localisation, int indiceDeProprete, Utilisateur utilisateur){
		this.quartier = quartier;
		this.description = description;
		this.photo = photo;
		this.photoBase64 = photoBase64;
		this.localisation = localisation;
		this.indiceDeProprete = indiceDeProprete;
		this.proprietaire = utilisateur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Quartier getQuartier() {
		return quartier;
	}

	public void setQuartier(Quartier quartier) {
		this.quartier = quartier;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getPhotoBase64() {
		return photoBase64;
	}

	public void setPhotoBase64(String photoBase64) {
		this.photoBase64 = photoBase64;
	}

	public Localisation getLocalisation() {
		return localisation;
	}

	public void setLocalisation(Localisation localisation) {
		this.localisation = localisation;
	}

	public int getIndiceDeProprete() {
		return indiceDeProprete;
	}

	public void setIndiceDeProprete(int indiceDeProprete) {
		this.indiceDeProprete = indiceDeProprete;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Utilisateur getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Utilisateur proprietaire) {
		this.proprietaire = proprietaire;
	}

	@Override
	public String toString() {
		return "Signalement [id=" + id + ", proprietaire=" + proprietaire + "]";
	}




}
