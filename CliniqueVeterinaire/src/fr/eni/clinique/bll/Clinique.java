package fr.eni.clinique.bll;

import java.util.List;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;

public class Clinique {
	
private List<Client> lesClients;
private List<Personnel> lesVeterinaires;
	
	private CliniqueManager manager;
	
	private Clinique() throws BLLException {
		manager = CliniqueManager.getInstance();
		lesClients = manager.getClients();
		lesVeterinaires = manager.getVeterinaires();
	}
	
	////////////////////////
	// SINGLETON
	////////////////////////
	private static Clinique instance = null;
	
	public static Clinique getInstance() throws BLLException {
		if(instance == null) {
			instance = new Clinique();
		}
		return instance;
	}

	
	////////////
	// GESTION DES CLIENTS AVEC LEURS ANIMAUX
	////////////
	public List<Client> getClients() {
		return lesClients;
	}
	
	
	/////////////
	// GESTION DU PERSONNEL
	/////////////

	public List<Personnel> getVeterinaires() {
		return lesVeterinaires;
	}
	
	
	
	
	
}
