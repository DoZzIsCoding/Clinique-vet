package fr.eni.clinique.bll;

import java.util.Date;
import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Espece;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.RDV;
import fr.eni.clinique.dal.AnimalDAO;
import fr.eni.clinique.dal.ClientDAO;
import fr.eni.clinique.dal.CreneauDejaPrisException;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.DalException;
import fr.eni.clinique.dal.EspeceDAO;
import fr.eni.clinique.dal.PersonnelDAO;
import fr.eni.clinique.dal.RDVDAO;

public class CliniqueManager {
	
private static CliniqueManager instance;
	
	private AnimalDAO animalDAO;
	private ClientDAO clientDAO;
	private PersonnelDAO personnelDAO;
	private RDVDAO rdvDAO;
	private EspeceDAO especeDAO;
	
	private CliniqueManager() { 
		animalDAO = DAOFactory.getAnimalDAO();
		clientDAO = DAOFactory.getClientDAO();
		personnelDAO = DAOFactory.getPersonnelDAO();
		rdvDAO = DAOFactory.getRDVDAO();
		especeDAO = DAOFactory.getEspeceDAO();
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

	/**
	 * Demande a la DAL de supprimer le RDV
	 * @param rdv
	 * @throws BLLException
	 */
	public void supprimerRdv(RDV rdv) throws BLLException{
		try {
			rdvDAO.supprimer(rdv);
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException("Erreur BLL supression de RDV");
		}
	}
	
	
	/**
	 * Demande a la DAL de supprimer le RDV
	 * @param rdv
	 * @throws BLLException
	 * @throws CreneauDejaPrisException
	 */
	public void ajouterRdv(RDV rdv) throws BLLException, CreneauDejaPrisException{
		try {
			rdvDAO.ajouter(rdv);		
		}catch (CreneauDejaPrisException e) {
			throw new CreneauDejaPrisException("Erreur BLL ajout de RDV");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BLLException("Erreur BLL ajout de RDV");
		}
	}

	/**
	 * demande a la DAL d'ajouter un Animal (contenant deja un codeClient valide)
	 * @param animal
	 * @throws DalException
	 */
	public void ajouterAnimal(Animal animal) throws DalException {
		try {
			
			//TODO: AJOUTER TOUTES LES VERIFICATION!!
			animalDAO.ajouter(animal);
		} catch (DalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DalException("Erreur BLL ajout d'animal");
		}
	}

	public List<Espece> getEspeces() {

		try {
			return especeDAO.selectionnerTout();
		} catch (DalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Connecte l'utilisateur avec son login et son mdp
	 * @param login
	 * @param mdp
	 * @throws DalException 
	 */
	public Personnel connecter(String login, String mdp) throws DalException {
		
		return personnelDAO.connecter(login, mdp);

	}
	
	
	
}
