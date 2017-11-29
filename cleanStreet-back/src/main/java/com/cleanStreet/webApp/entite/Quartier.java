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

@Entity
public class Quartier implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nom;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="quartier", cascade = CascadeType.ALL)
	private List<Localisation> listeLcocalisation;

	public Quartier(String nom, List<Localisation> listeLcocalisation) {
		this.nom = nom;
		this.listeLcocalisation = listeLcocalisation;
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
	public List<Localisation> getListeLcocalisation() {
		return listeLcocalisation;
	}
	public void setListeLcocalisation(List<Localisation> listeLcocalisation) {
		this.listeLcocalisation = listeLcocalisation;
	}
}
