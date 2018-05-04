package fr.eni.clinique.bo;

public class Animal {

	private int codeAnimal;
	private String nomAnimal;
	private char sexe;
	private String couleur;
	private String race;
	private String espece;
	private int codeClient;
	private String tatouage;
	private String antecedents;
	private boolean archive;

	private RDV rdv;

	public Animal() {
	}

	public Animal(int codeAnimal, String nomAnimal, char sexe, String couleur, String race, String espece,
			int codeClient, String tatouage, String antecedents) {
		super();
		this.codeAnimal = codeAnimal;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.race = race;
		this.espece = espece;
		this.codeClient = codeClient;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
		this.archive = false;
		this.rdv = new RDV();

	}

	// METHODES

	public void ajouterRdv(RDV rdv) {
		this.rdv = rdv;
	}

	public void supprimerRdv() {
		this.rdv = null;
	}

	// GETTERS AND SETTERS
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

	public char getSexe() {
		return sexe;
	}

	public void setSexe(char sexe) {
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

	public int getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(int codeClient) {
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Animal [getCodeAnimal()=");
		builder.append(getCodeAnimal());
		builder.append(", getNomAnimal()=");
		builder.append(getNomAnimal());
		builder.append(", getSexe()=");
		builder.append(getSexe());
		builder.append(", getCouleur()=");
		builder.append(getCouleur());
		builder.append(", getRace()=");
		builder.append(getRace());
		builder.append(", getEspece()=");
		builder.append(getEspece());
		builder.append(", getCodeClient()=");
		builder.append(getCodeClient());
		builder.append(", getTatouage()=");
		builder.append(getTatouage());
		builder.append(", getAntecedents()=");
		builder.append(getAntecedents());
		builder.append(", isArchive()=");
		builder.append(isArchive());
		builder.append("]");
		return builder.toString();
	}

	
}
