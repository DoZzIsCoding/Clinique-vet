package fr.eni.clinique.bo;


import java.util.Date;
import java.util.GregorianCalendar;

public class RDV {
	
	
	//VARIABLES
	private Date date;
	private String nomClient;
	private String nomAnimal;
	private String especeAnimal;
	

	//CONSTRUCTEUR
	public RDV() {
		// TODO Auto-generated constructor stub
	}
	
	public RDV(Date date, String nomClient, String nomAnimal, String especeAnimal) {
		super();
		this.date = date;
		this.nomClient = nomClient;
		this.nomAnimal = nomAnimal;
		this.especeAnimal = especeAnimal;
	}
	
	
	// SETTERS GETTERS
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getNomAnimal() {
		return nomAnimal;
	}

	public void setNomAnimal(String nomAnimal) {
		this.nomAnimal = nomAnimal;
	}

	public String getEspeceAnimal() {
		return especeAnimal;
	}

	public void setEspeceAnimal(String especeAnimal) {
		this.especeAnimal = especeAnimal;
	}




	

	
}
