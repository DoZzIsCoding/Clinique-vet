package fr.eni.clinique.bo;

import java.util.ArrayList;
import java.util.List;

public class Espece {
	
	//VARIABLES
	private String nomEspece;
	private List<String> races = new ArrayList<String>();
	
	
	// CONSTRUCTEURS
	public Espece(String nomEspece, List<String> races) {
		super();
		this.nomEspece = nomEspece;
		this.races = races;
	}



	//GETTER SETTERS
	public String getNomEspece() {
		return nomEspece;
	}


	public void setNomEspece(String nomEspece) {
		this.nomEspece = nomEspece;
	}


	public List<String> getRaces() {
		return races;
	}


	public void setRaces(List<String> races) {
		this.races = races;
	}
	
	// METHODES
	public void ajouterRace(String race) {
		this.races.add(race);
	}



	//ToString
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Espece [nomEspece=");
		builder.append(nomEspece);
		builder.append(", races=");
		builder.append(races);
		builder.append("]");
		return builder.toString();
	}


	
	
	
	
	
}
