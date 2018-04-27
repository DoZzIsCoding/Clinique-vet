package fr.eni.clinique.dal;

import java.util.List;

import fr.eni.clinique.bo.Animal;

public interface AnimalDAO extends DAO<Animal>{

	/**
	 * 
	 * @param id
	 * @return un animal en fonction de son id ou null s'il n'existe pas
	 */
	public abstract Animal selectionnerUn(int id);
	
	
	/**
	 * 
	 * @return la liste des animaux jamais null
	 * @throws DalException 
	 */
	public abstract List<Animal> selectionnerTout() throws DalException;
	
	
	/**
	 * 
	 * @param animal non null
	 * @throws DalException 
	 */
	public abstract void ajouter(Animal animal) throws DalException;
	
	/**
	 * 
	 * @param animal non null
	 * @throws DalException 
	 */
	public abstract void modifier(Animal animal) throws DalException;
	
	/**
	 * 
	 * @param animal non null
	 * @throws DalException 
	 */
	public abstract boolean supprimer(Animal animal) throws DalException;


	
	
	
	
}
