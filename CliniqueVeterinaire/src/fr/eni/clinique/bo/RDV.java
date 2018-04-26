package fr.eni.clinique.bo;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

public class RDV {
	
	
	//VARIABLES
	private LocalDateTime date;
	private String nomClient;
	private String nomAnimal;
	private String especeAnimal;
	private int codeVeto;
	

	//CONSTRUCTEUR
	public RDV() {
		// TODO Auto-generated constructor stub
	}
	
	public RDV(LocalDateTime date, String nomClient, String nomAnimal, String especeAnimal) {
		super();
		this.date = date;
		this.nomClient = nomClient;
		this.nomAnimal = nomAnimal;
		this.especeAnimal = especeAnimal;
	}
	
	public RDV(LocalDateTime date, String nomClient, String nomAnimal, String especeAnimal, int codeVeto) {
		super();
		this.date = date;
		this.nomClient = nomClient;
		this.nomAnimal = nomAnimal;
		this.especeAnimal = especeAnimal;
		this.codeVeto = codeVeto;
	}

	// SETTERS GETTERS
	
	public int getCodeVeto() {
		return codeVeto;
	}

	public void setCodeVeto(int codeVeto) {
		this.codeVeto = codeVeto;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
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
