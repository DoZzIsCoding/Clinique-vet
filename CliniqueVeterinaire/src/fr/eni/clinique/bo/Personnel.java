package fr.eni.clinique.bo;

public class Personnel {

	private int codePers;
	private String nom;
	private String motDePasse;
	private String role;
	private boolean archive;
	
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
	}

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
	
	
	
	
}
