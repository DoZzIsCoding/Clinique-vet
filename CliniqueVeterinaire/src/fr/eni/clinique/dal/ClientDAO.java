package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.exceptions.DalException;

public interface ClientDAO extends DAO<Client>{

	/**
	 * 
	 * @param id
	 * @return un client en fonction de son id ou null s'il n'existe pas
	 * @throws DalException 
	 */
	public abstract Client selectionnerUn(int id) throws DalException;
	
	
	/**
	 * 
	 * @return la liste des clients jamais null
	 * @throws DalException 
	 */
	public abstract List<Client> selectionnerTout() throws DalException;
	
	
	/**
	 * 
	 * @param client non null
	 * @throws DalException 
	 */
	public abstract void ajouter(Client client) throws DalException;
	
	/**
	 * 
	 * @param client non null
	 * @throws DalException 
	 */
	public abstract void modifier(Client client) throws DalException;
	
	/**
	 * 
	 * @param client non null
	 * @throws DalException 
	 */
	public abstract boolean supprimer(Client client) throws DalException;
	
	/**
	 * selectionne les clients avec leur liste d'animaux respective
	 * @return
	 * @throws DalException
	 */
	public List<Client> selectionnerAvecAnimaux() throws DalException;


	public abstract void traiterClient(Client client) throws DalException;

}
