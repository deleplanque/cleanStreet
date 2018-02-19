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
@Table(name="contact")
public class Contact implements Serializable{

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
	private String objet;
    private String contenu;



	public Contact() {
		super();
	}


	public Contact(int id, String nom, String prenom, String email, String objet, String contenu) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
        this.objet=objet;
        this.contenu = contenu;
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


    public String getObjet(){
        return objet;
    }

    public void setObjet(String objet){
        this.objet = objet;
    }


    public String getContenu(){
        return contenu;
    }

    public void setContenu(String contenu){
        this.contenu = contenu;
    }



	@Override
	public String toString() {
		return "Contenu [id=" + id + ", nom=" + nom + ", prenom=" 
        + prenom + ", objet = " + objet +",contenu=" + contenu + "]";
	}




}
