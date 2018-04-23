package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.bo.Client;

public interface ClientDAO extends DAO<Client>{

	/**
	 * 
	 * @param id
	 * @return un client en fonction de son id ou null s'il n'existe pas
	 */
	public abstract Client selectionnerUn(int id);
	
	
	/**
	 * 
	 * @return la liste des clients jamais null
	 */
	public abstract List<Client> selectionnerTout();
	
	
	/**
	 * 
	 * @param client non null
	 */
	public abstract void ajouter(Client client);
	
	/**
	 * 
	 * @param client non null
	 */
	public abstract void modifier(Client client);
	
	/**
	 * 
	 * @param client non null
	 */
	public abstract boolean supprimer(Client client);

}
