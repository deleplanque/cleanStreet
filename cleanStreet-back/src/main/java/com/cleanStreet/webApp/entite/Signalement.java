package com.cleanStreet.webApp.entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Signalement implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String quartier;
	private String description;
	private String photo;
	private String localisation;
	private int indiceDeProprete;

	@ManyToOne
//	@JoinColumn(name = "utilisateurs_id")
	private Utilisateur proprietaire;

	public Signalement(){}

	public Signalement(String quartier,String description, String photo, String localisation, int indiceDeProprete, Utilisateur utilisateur){
		this.quartier = quartier;
		this.description = description;
		this.photo = photo;
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

	public String getQuartier() {
		return quartier;
	}

	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
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
