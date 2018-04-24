package fr.eni.clinique.bll;

import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.AnimalDAO;
import fr.eni.clinique.dal.ClientDAO;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.DalException;

public class CliniqueManager {
	
private static CliniqueManager instance;
	
	private AnimalDAO animalDAO;
	private ClientDAO clientDAO;
	
	private CliniqueManager() { 
		animalDAO = DAOFactory.getAnimalDAO();
		clientDAO = DAOFactory.getClientDAO();
	}
	
	public static CliniqueManager getInstance() {
		if(instance == null) {
			instance = new CliniqueManager();
		}
		return instance;
	}
	
	public List<Animal> getAnimaux() throws BLLException {
		try {
			return animalDAO.selectionnerTout();
		} catch (DalException e) {
			throw new BLLException("Erreur accès aux animaux.");
		}
	}
	
	
	/**
	 * 
	 * @return la liste des clients avec leurs animaux
	 * @throws BLLException
	 */
	public List<Client> getClients() throws BLLException {
		try {
			return clientDAO.selectionnerTout();
		} catch (DalException e) {
			throw new BLLException("Erreur accès aux clients.");
		}
	}

}
