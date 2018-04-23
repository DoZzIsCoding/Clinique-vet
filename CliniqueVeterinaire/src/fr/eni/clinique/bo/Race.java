package fr.eni.clinique.bo;

public class Race {
	
	//VARIABLES
	private String race;
	private String espece;

	
	//CONSTRUCTEUR
	public Race(String race, String espece) {
		super();
		this.race = race;
		this.espece = espece;
	}
	
	// GETTERS ET SETTERS
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getEspece() {
		return espece;
	}
	public void setEspece(String espece) {
		this.espece = espece;
	}
	
	
	
	
}
