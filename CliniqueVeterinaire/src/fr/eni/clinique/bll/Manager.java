package fr.eni.clinique.bll;

import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.AnimalDAO;
import fr.eni.clinique.dal.ClientDAO;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.DalException;

public class Manager {
	
private static Manager instance;
	
	private AnimalDAO animalDAO;
	private ClientDAO clientDAO;
	
	private Manager() { 
		animalDAO = DAOFactory.getAnimalDAO();
		clientDAO = DAOFactory.getClientDAO();
	}
	
	public static Manager getInstance() {
		if(instance == null) {
			instance = new Manager();
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
	
	public List<Client> getClients() throws BLLException {
		try {
			return clientDAO.selectionnerTout();
		} catch (DalException e) {
			throw new BLLException("Erreur accès aux clients.");
		}
	}
	

}
