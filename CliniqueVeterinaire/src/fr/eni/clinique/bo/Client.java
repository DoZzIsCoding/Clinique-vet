package fr.eni.clinique.bo;

import java.util.ArrayList;
import java.util.List;

public class Client {

	// VARIABLES
	private int codeClient;
	private String nomClient;
	private String prenomClient;
	private String adresse1;
	private String adresse2;
	private String codePostal;
	private String ville;
	private String numTel;
	private String assurance;
	private String email;
	private String remarque;
	private Boolean archive;
	
	private List<Animal> animaux;
	
	
	//CONSTRUCTEUR
	public Client(int codeClient, String nomClient, String prenomClient, String adresse1, String adresse2,
			String codePostal, String ville, String numTel, String assurance, String email, String remarque) {
		super();
		this.codeClient = codeClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.numTel = numTel;
		this.assurance = assurance;
		this.email = email;
		this.remarque = remarque;
		this.archive = false;
		this.animaux = new ArrayList<>();
	}
	
	//METHODES 
	public void ajouterAnimal(Animal animal){
		this.animaux.add(animal);
	}
	
	public void supprimerAnimal(Animal animal){
		this.animaux.remove(animal);
	}
	
	public void modifierAnimal(Animal animal){
		this.animaux.set(this.animaux.indexOf(animal) ,animal);
	}
	
	//GETTERS SETTERS
	public List<Animal> getAnimaux(){
		return animaux;
	}
	
	
	public int getCodeClient() {
		return codeClient;
	}
	
	public void setCodeClient(int codeClient) {
		this.codeClient = codeClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
	public String getPrenomClient() {
		return prenomClient;
	}
	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}
	public String getAdresse1() {
		return adresse1;
	}
	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}
	public String getAdresse2() {
		return adresse2;
	}
	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	public String getAssurance() {
		return assurance;
	}
	public void setAssurance(String assurance) {
		this.assurance = assurance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemarque() {
		return remarque;
	}
	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}
	public Boolean getArchive() {
		return archive;
	}
	public void setArchive(Boolean archive) {
		this.archive = archive;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("getCodeClient()=");
		builder.append(getCodeClient());
		builder.append(", getNomClient()=");
		builder.append(getNomClient());
		builder.append(", getPrenomClient()=");
		builder.append(getPrenomClient());
		builder.append(", getAdresse1()=");
		builder.append(getAdresse1());
		builder.append(", getAdresse2()=");
		builder.append(getAdresse2());
		builder.append(", getCodePostal()=");
		builder.append(getCodePostal());
		builder.append(", getVille()=");
		builder.append(getVille());
		builder.append(", getNumTel()=");
		builder.append(getNumTel());
		builder.append(", getAssurance()=");
		builder.append(getAssurance());
		builder.append(", getEmail()=");
		builder.append(getEmail());
		builder.append(", getRemarque()=");
		builder.append(getRemarque());
		builder.append(", getArchive()=");
		builder.append(getArchive());
		builder.append("Client [getAnimaux()=");
		builder.append(getAnimaux());
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
