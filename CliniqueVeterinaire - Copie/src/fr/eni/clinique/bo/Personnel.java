package fr.eni.clinique.bo;

import java.util.ArrayList;
import java.util.List;

public class Personnel {

	private int codePers;
	private String nom;
	private String motDePasse;
	private String role;
	private boolean archive;
	
	private List<RDV> planning;
	
	public Personnel() {
		// TODO Auto-generated constructor stub
	}
	
	public Personnel(int codePers, String nom, String motDePasse, String role) {
		super();
		this.codePers = codePers;
		this.nom = nom;
		this.motDePasse = motDePasse;
		this.role = role;
		this.archive = false;
		this.planning = new ArrayList<>();
	}

	// METHODES

	public void ajouterRdv (RDV rdv){
		this.planning.add(rdv);
	}
	
	public void supprimerRdv(RDV rdv){
		this.planning.remove(rdv);
	}
	
	public void modifierRdv (){
		
	}
	// GETTERS AND SETTERS
	public int getCodePers() {
		return codePers;
	}
	public void setCodePers(int codePers) {
		this.codePers = codePers;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
		builder.append("Personnel [getCodePers()=");
		builder.append(getCodePers());
		builder.append(", getNom()=");
		builder.append(getNom());
		builder.append(", getMotDePasse()=");
		builder.append(getMotDePasse());
		builder.append(", getRole()=");
		builder.append(getRole());
		builder.append(", isArchive()=");
		builder.append(isArchive());
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
