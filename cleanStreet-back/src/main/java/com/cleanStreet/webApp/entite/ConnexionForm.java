package com.cleanStreet.webApp.entite;

public class ConnexionForm {

	private String email;
	private String motDePasse;

	public ConnexionForm(){}

	public ConnexionForm(String email, String motDePasse) {
		this.email = email;
		this.motDePasse = motDePasse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}



}
