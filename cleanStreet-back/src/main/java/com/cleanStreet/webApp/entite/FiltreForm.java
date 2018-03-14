package com.cleanStreet.webApp.entite;

public class FiltreForm {

	private int perimetre;
	private String quartier;
	private double lat;
	private double lng;
	
	
public FiltreForm() {
}

	public FiltreForm(int perimetre, String quartier, double lat, double lng) {
		this.perimetre = perimetre;
		this.quartier = quartier;
		this.lat = lat;
		this.lng = lng;
	}

	public int getPerimetre() {
		return perimetre;
	}

	public void setPerimetre(int perimetre) {
		this.perimetre = perimetre;
	}

	public String getQuartier() {
		return quartier;
	}

	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	
}
