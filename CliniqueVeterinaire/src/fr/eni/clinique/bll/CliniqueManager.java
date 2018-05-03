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
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.EspeceDAO;
import fr.eni.clinique.dal.PersonnelDAO;
import fr.eni.clinique.dal.RDVDAO;
import fr.eni.clinique.exceptions.AnimalNonValideException;
import fr.eni.clinique.exceptions.BLLException;
import fr.eni.clinique.exceptions.ClientNonValideException;
import fr.eni.clinique.exceptions.CreneauDejaPrisException;
import fr.eni.clinique.exceptions.DalException;

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
		if (instance == null) {
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
	 * 
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
	 * 
	 * @param rdv
	 * @throws BLLException
	 */
	public void supprimerRdv(RDV rdv) throws BLLException {
		try {
			rdvDAO.supprimer(rdv);
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException("Erreur BLL supression de RDV");
		}
	}

	/**
	 * Demande a la DAL d'ajouter le RDV
	 * 
	 * @param rdv
	 * @throws BLLException
	 * @throws CreneauDejaPrisException
	 */
	public void ajouterRdv(RDV rdv) throws BLLException, CreneauDejaPrisException {
		try {
			rdvDAO.ajouter(rdv);
		} catch (CreneauDejaPrisException e) {
			throw new CreneauDejaPrisException("Erreur BLL ajout de RDV");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BLLException("Erreur BLL ajout de RDV");
		}
	}

	/**
	 * demande a la DAL d'ajouter un Animal (contenant deja un codeClient valide)
	 * 
	 * @param animal
	 * @throws DalException
	 * @throws AnimalNonValideException 
	 * @throws BLLException 
	 */
	public void traiterAnimal(Animal animal) throws AnimalNonValideException, BLLException {
		try {
			validerAnimal(animal);
			animalDAO.traiterAnimal(animal);

		} catch (DalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BLLException("Erreur BLL ajout d'animal");
		}
	}

	private void validerAnimal(Animal animal) throws AnimalNonValideException {
		// TODO Auto-generated method stub
		Boolean animalOK = true;
		AnimalNonValideException e = new AnimalNonValideException();
		if (animal.getNomAnimal().length() > 30) {
			e.ajouterErreur("Le nom de ne doit pas dépasser 30 caractères");
			animalOK = false;
		}
		if (animal.getNomAnimal().trim().length() == 0) {
			e.ajouterErreur("Le nom est obligatoire");
			animalOK = false;
		}
		if (animal.getCouleur().length() > 20) {
			e.ajouterErreur("La couleur ne doit pas dépasser 20 caractères");
			animalOK = false;
		}
		if (animal.getTatouage().length() > 10) {
			e.ajouterErreur("Le tatouage ne doit pas dépasser 10 caractères");
			animalOK = false;
		}
		if(!animalOK) throw e;

	}
	
	public boolean supprimerAnimal(Animal animal) throws BLLException{
		try {
			animalDAO.supprimer(animal);
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException("Erreur de suppression dans la base");
		}
		return true;
	}
	
	public void validerClient(Client client) throws ClientNonValideException, BLLException{
		// TODO Auto-generated method stub
		Boolean clientOK = true;
		ClientNonValideException e = new ClientNonValideException();
		if (client.getNomClient().length() > 20 ) {
			e.ajouterErreur("Le nom de ne doit pas dépasser 20 caractères");
			clientOK = false;
		}
		if (client.getNomClient().length() == 0 ) {
			e.ajouterErreur("Le nom est obligatoire");
			clientOK = false;
		}
		if (client.getPrenomClient().length() > 20) {
			e.ajouterErreur("Le prénom de ne doit pas dépasser 20 caractères");
			clientOK = false;
		}
		if (client.getPrenomClient().length() == 0  ) {
			e.ajouterErreur("Le prénom est obligatoire");
			clientOK = false;
		}
		if (client.getAdresse1().length() > 30 || client.getAdresse2().length() > 30) {
			e.ajouterErreur("La taille de l'adresse ne doit pas dépasser 30 caractères par ligne");
			clientOK = false;
		}
		if (client.getCodePostal().length() > 6) {
			e.ajouterErreur("Le code postal ne doit pas dépasser 6 caractères");
			clientOK = false;
		}
		if (client.getCodePostal().length() > 6) {
			e.ajouterErreur("Le numero de telephone ne doit pas dépasser 15 caractères");
			clientOK = false;
		}		
		if (client.getVille().length() > 25) {
			e.ajouterErreur("La ville ne doit pas dépasser 25 caractères");
			clientOK = false;
		}
		if (client.getNumTel().length() > 15) {
			e.ajouterErreur("Le numéro de téléphone ne doit pas dépasser 15 caractères");
			clientOK = false;
		}
		if (client.getAssurance().length() > 30) {
			e.ajouterErreur("Le numéro de police d'assurance ne doit pas dépasser 30 caractères");
			clientOK = false;
		}
		if (client.getEmail().length() > 20) {
			e.ajouterErreur("L'adresse email ne doit pas dépasser 20 caractères");
			clientOK = false;
		}
				
		if(!clientOK) throw e;
		
		try {
			clientDAO.traiterClient(client);
		} catch (DalException e1) {
			e1.printStackTrace();
			throw new BLLException("Erreur validation de client");
		}

	}

	public void supprimerClient(Client clientASupprimer) throws BLLException {
		try {
			clientDAO.supprimer(clientASupprimer);
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException("Erreur de suppression");
		}
	}
	
	public void traiterClient(Client client) throws BLLException, ClientNonValideException {
		try {
			validerClient(client);
			clientDAO.traiterClient(client);
			
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException("Erreur d'ajout ");
		} catch (ClientNonValideException e) {
			e.printStackTrace();
			throw e;
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
	 * 
	 * @param login
	 * @param mdp
	 * @throws DalException
	 */
	public Personnel connecter(String login, String mdp) throws DalException {

		return personnelDAO.connecter(login, mdp);

	}

	public List<Personnel> getPersonnel() {
		try {
			return personnelDAO.selectionnerTout();
		} catch (DalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	

	

}
