package com.cleanStreet.webApp.entite;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Quartier implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nom;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="quartier", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Signalement> listSignalements;

	public Quartier() {
	}

	public Quartier(int id, String nom){
		this.id = id;
		this.nom = nom;
	}

	public Quartier(String nom, List<Signalement> listSignalements) {
		this.nom = nom;
		this.listSignalements = listSignalements;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Signalement> getListSignalements() {
		return listSignalements;
	}

	public void setListSignalements(List<Signalement> listSignalements) {
		this.listSignalements = listSignalements;
	}

}
