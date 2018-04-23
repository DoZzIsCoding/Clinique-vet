package fr.eni.clinique.bo;


import java.util.GregorianCalendar;

public class Agenda {
	
	
	//VARIABLES
	private int codeVeto;
	private GregorianCalendar dateRDV;
	private int codeAnimal;
	

	//CONSTRUCTEUR
	public Agenda() {
		// TODO Auto-generated constructor stub
	}
	
	public Agenda(int codeVeto, GregorianCalendar dateRDV, int codeAnimal) {
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
	public GregorianCalendar getDateRDV() {
		return dateRDV;
	}
	public void setDateRDV(GregorianCalendar dateRDV) {
		this.dateRDV = dateRDV;
	}
	public int getCodeAnimal() {
		return codeAnimal;
	}
	public void setCodeAnimal(int codeAnimal) {
		this.codeAnimal = codeAnimal;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Agenda [getCodeVeto()=");
		builder.append(getCodeVeto());
		builder.append(", getDateRDV()=");
		builder.append(getDateRDV().getTime());
		builder.append(", getCodeAnimal()=");
		builder.append(getCodeAnimal());
		builder.append("]");
		return builder.toString();
	}
	

	
}
