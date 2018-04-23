package fr.eni.clinique.bo;

import java.util.Date;

public class Agenda {
	
	
	//VARIABLES
	private int codeVeto;
	private Date dateRDV;
	private int codeAnimal;
	

	//CONSTRUCTEUR
	public Agenda() {
		// TODO Auto-generated constructor stub
	}
	
	public Agenda(int codeVeto, Date dateRDV, int codeAnimal) {
		super();
		this.codeVeto = codeVeto;
		this.dateRDV = dateRDV;
		this.codeAnimal = codeAnimal;
	}
	
	
	// SETTERS GETTERS
	public int getCodeVeto() {
		return codeVeto;
	}
	public void setCodeVeto(int codeVeto) {
		this.codeVeto = codeVeto;
	}
	public Date getDateRDV() {
		return dateRDV;
	}
	public void setDateRDV(Date dateRDV) {
		this.dateRDV = dateRDV;
	}
	public int getCodeAnimal() {
		return codeAnimal;
	}
	public void setCodeAnimal(int codeAnimal) {
		this.codeAnimal = codeAnimal;
	}
	

}
