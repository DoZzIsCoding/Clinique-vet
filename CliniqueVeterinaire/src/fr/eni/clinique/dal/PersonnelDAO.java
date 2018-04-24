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
	 * 
	 */
	public abstract List<Personnel> selectionnerTout();
	
	
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

}