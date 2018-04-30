package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.bo.Personnel;

public interface PersonnelDAO extends DAO<Personnel>{

	/**
	 * 
	 * @param id
	 * @return un personnel en fonction de son id ou null s'il n'existe pas
	 */
	public abstract Personnel selectionnerUn(int id);
	
	
	/**
	 * 
	 * @return la liste du personnel (non Archivé) jamais null
	 * @throws DalException 
	 * 
	 */
	public abstract List<Personnel> selectionnerTout() throws DalException;
	
	
	/**
	 * 
	 * @return la liste des veterinaires (non Archivés) jamais null
	 * @throws DalException 
	 * 
	 */
	public abstract List<Personnel> selectionnerVetos() throws DalException;
	
	/**
	 * 
	 * @param personnel non null
	 */
	public abstract void ajouter(Personnel personnel);
	
	/**
	 * 
	 * @param personnel non null
	 */
	public abstract void modifier(Personnel personnel);
	
	/**
	 * 
	 * @param personnel non null
	 */
	public abstract boolean supprimer(Personnel personnel);
	
	public abstract void connecter(String login, String mdp);

}