package fr.eni.clinique.bll;

import java.util.Date;
import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.RDV;
import fr.eni.clinique.dal.AnimalDAO;
import fr.eni.clinique.dal.ClientDAO;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.DalException;
import fr.eni.clinique.dal.PersonnelDAO;
import fr.eni.clinique.dal.RDVDAO;

public class CliniqueManager {
	
private static CliniqueManager instance;
	
	private AnimalDAO animalDAO;
	private ClientDAO clientDAO;
	private PersonnelDAO personnelDAO;
	private RDVDAO rdvDAO;
	
	private CliniqueManager() { 
		animalDAO = DAOFactory.getAnimalDAO();
		clientDAO = DAOFactory.getClientDAO();
		personnelDAO = DAOFactory.getPersonnelDAO();
		rdvDAO = DAOFactory.getRDVDAO();
	}
	
	public static CliniqueManager getInstance() {
		if(instance == null) {
			instance = new CliniqueManager();
		}
		return instance;
	}
	
	
	/**
	 * 
	 * @return la liste des animaux (non archivés)
	 * @throws BLLException
	 */
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
			return clientDAO.selectionnerAvecAnimaux();
		} catch (DalException e) {
			throw new BLLException("Erreur accès aux clients (avec animaux).");
		}
	}
	
	/**
	 * 
	 * @return la liste des veterinaires (sans les mots de passe) 
	 * @throws BLLException
	 */
	public List<Personnel> getVeterinaires() throws BLLException {
		try {
			return personnelDAO.selectionnerVetos();
		} catch (DalException e) {
			throw new BLLException("Erreur accès aux Veterinaires .");
		}
	}

	/**
	 * Recupere la liste des RDV prévus a une date donnée avec un veterinaire donné
	 * @param date
	 * @return Liste de RDV.
	 * @throws BLLException 
	 */
	public List<RDV> getRDVdu(Date date, Personnel veterinaire) throws BLLException {
		try {
			return rdvDAO.selectionnerRDV(date, veterinaire);
		} catch (DalException e) {
			throw new BLLException("Erreur accès aux Rendez vous .");
		}
	}

	public void supprimerRdv(RDV rdv){}
}
