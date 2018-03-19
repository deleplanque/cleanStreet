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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="utilisateurs")
public class Utilisateur implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String motDePasse;
	private int droit;

	public int getDroit() {
		return droit;
	}


	public void setDroit(int droit) {
		this.droit = droit;
	}


	@OneToMany(fetch = FetchType.LAZY, mappedBy="proprietaire", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Signalement> signalements;


	public Utilisateur() {
		super();
	}


	public Utilisateur(int id, String nom, String prenom, String email, List<Signalement> signalements, int droit) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.signalements = signalements;
		this.droit = droit;
	}

	public Utilisateur(int id, String nom, String prenom, String email, String motDePasse, int droit) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.droit = droit;
	}


	public String getMotDePasse() {
		return motDePasse;
	}


	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
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


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Signalement> getSignalements() {
		return signalements;
	}


	public void setSignalements(List<Signalement> signalements) {
		this.signalements = signalements;
	}


	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}




}
