package fr.eni.clinique.bll;

import java.util.List;

import fr.eni.clinique.bo.Client;

public class Clinique {
	
private List<Client> lesClients;
	
	private CliniqueManager manager;
	
	private Clinique() throws BLLException {
		manager = CliniqueManager.getInstance();
		
		lesClients = manager.getClients();
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
	
	
	
	
}
