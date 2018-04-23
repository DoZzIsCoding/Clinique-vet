package fr.eni.clinique.bo;

public class Animal {

	
	private int codeAnimal;
	private String nomAnimal;
	private String sexe;
	private String couleur;
	private String race;
	private String espece;
	private long codeClient;
	private String tatouage;
	private String antecedents;
	private boolean archive;
	
	private Agenda rdv;
	
	public Animal() {
		// TODO Auto-generated constructor stub
	}

	

	public Animal(int codeAnimal, String nomAnimal, String sexe, String couleur, String race, String espece,
			long codeClient, String tatouage) {
		super();
		this.codeAnimal = codeAnimal;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.race = race;
		this.espece = espece;
		this.codeClient = codeClient;
		this.tatouage = tatouage;
		this.antecedents = "";
		this.archive = false;
		this.rdv = new Agenda();
		
	}



	public int getCodeAnimal() {
		return codeAnimal;
	}


	public void setCodeAnimal(int codeAnimal) {
		this.codeAnimal = codeAnimal;
	}


	public String getNomAnimal() {
		return nomAnimal;
	}


	public void setNomAnimal(String nomAnimal) {
		this.nomAnimal = nomAnimal;
	}


	public String getSexe() {
		return sexe;
	}


	public void setSexe(String sexe) {
		this.sexe = sexe;
	}


	public String getCouleur() {
		return couleur;
	}


	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}


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


	public long getCodeClient() {
		return codeClient;
	}


	public void setCodeClient(long codeClient) {
		this.codeClient = codeClient;
	}


	public String getTatouage() {
		return tatouage;
	}


	public void setTatouage(String tatouage) {
		this.tatouage = tatouage;
	}


	public String getAntecedents() {
		return antecedents;
	}


	public void setAntecedents(String antecedents) {
		this.antecedents = antecedents;
	}


	public boolean isArchive() {
		return archive;
	}


	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	
	
	
}
