package fr.eni.clinique.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ListModel;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Espece;
import fr.eni.clinique.bo.Observable;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.RDV;
import fr.eni.clinique.dal.CreneauDejaPrisException;
import fr.eni.clinique.dal.DalException;

public class Clinique {

	private List<Client> lesClients;
	private List<Personnel> lesVeterinaires;
	private List<Personnel> lePersonnel;
	private List<RDV> lesRdv;
	private List<Espece> lesEspeces;

	// TODO: repasser l'indexclientencours a -1 apres les test
	private int indexClientEnCours = 0;
	private int indexAnimalEnCours = -1;
	private Observable<Integer> clientEnCours;
	//clientEnCours = new Observable<>(0);
	
	private Personnel utilisateurConnecté = null;

	private CliniqueManager manager;

	private Clinique() throws BLLException {
		manager = CliniqueManager.getInstance();
		lesClients = manager.getClients();
		lesVeterinaires = manager.getVeterinaires();
		clientEnCours = new Observable<>(0);
	}

	// GETTERS SETTERS

	public Personnel getUtilisateurConnecté() {
		return utilisateurConnecté;
	}

	public void setUtilisateurConnecté(Personnel utilisateurConnecté) {
		this.utilisateurConnecté = utilisateurConnecté;
	}

	public int getIndexClientEnCours() {
		return indexClientEnCours;
	}

	public void setIndexClientEnCours(int indexClientEnCours) {
		this.indexClientEnCours = indexClientEnCours;
	}

	public int getIndexAnimalEnCours() {
		return indexAnimalEnCours;
	}

	public void setIndexAnimalEnCours(int indexAnimalEnCours) {
		this.indexAnimalEnCours = indexAnimalEnCours;
	}

	////////////////////////
	// SINGLETON
	////////////////////////
	private static Clinique instance = null;

	public static Clinique getInstance() throws BLLException {
		if (instance == null) {
			instance = new Clinique();
		}
		return instance;
	}

	////////////
	// GESTION DES CLIENTS (AVEC LEURS ANIMAUX)
	////////////
	public List<Client> getClients() {
		return lesClients;
	}

	/**
	 * 
	 * @return un tableau de String contenant les noms Client.
	 */
	public String[] getTabNomsClients() {
		String[] tableau = new String[this.getClients().size()];
		for (int i = 0; i < tableau.length; i++) {
			tableau[i] = lesClients.get(i).getNomClient() + " " + lesClients.get(i).getPrenomClient();
		}
		return tableau;
	}

	/**
	 * 
	 * @param indexclient
	 *            complet (avec Liste d'animaux)
	 * @return un tableau de String contenant les noms des animaux du client.
	 */
	public String[] getAnimauxDeClient(int index) {
		String[] tableau = new String[lesClients.get(index).getAnimaux().size()];
		for (int i = 0; i < tableau.length; i++) {
			tableau[i] = lesClients.get(index).getAnimaux().get(i).getNomAnimal();
		}
		return tableau;
	}

	public void ajouterAnimal(Animal animal) throws AnimalNonValideException, BLLException {

		manager.ajouterAnimal(animal);
		getClientEnCours().ajouterAnimal(animal);

	}

	public Client getClientEnCours() {
		if (indexClientEnCours == -1) {
			return null;
		} else {
			return lesClients.get(indexClientEnCours);
		}
	}

	public Animal getAnimalEnCours() {
		if (indexClientEnCours == -1 || indexAnimalEnCours == -1) {
			return null;
		} else {
			return lesClients.get(indexClientEnCours).getAnimaux().get(indexAnimalEnCours);
		}
	}

	/**
	 * Recherches dans les données locales les clients dont le nom contient le Mot
	 * en parametre.
	 * 
	 * @param mot
	 * @return liste de Client
	 */
	public List<Client> rechercherClients(String mot) {
		List<Client> resultatList = new ArrayList<>();
		for (Client c : lesClients) {
			if(c.getNomClient().indexOf(mot)!=-1 || c.getPrenomClient().indexOf(mot)!=-1)
				resultatList.add(c);
		}
		return resultatList;
	}
	
	public void supprimerClientCourant() throws BLLException {
		Client clientASupprimer = lesClients.get(getIndexClientEnCours());
		
		try {
			manager.supprimerClient(clientASupprimer);
			lesClients.remove(getIndexClientEnCours());
		} catch (BLLException e) {
			e.printStackTrace();
			throw new BLLException("Erreur : suppression client non effectuée");
		}
		
	}

	
	public void traiterClient(Client client) throws BLLException, ClientNonValideException {
		try {
			manager.traiterClient(client);
			if(client.getCodeClient()==-1){
				getClients();
			}
		} catch (BLLException e) {
			e.printStackTrace();
			throw new BLLException("Erreur accès aux données");
		}
	}
	
	/////////////////
	// GESTION DES ANIMAUX
	/////////////////

	public List<Espece> getEspeces() {
		lesEspeces = manager.getEspeces();
		System.out.println(lesEspeces);
		return lesEspeces;

	}

	public String[] getTabEspeces() {
		System.out.println(getEspeces().size());
		String[] tableau = new String[getEspeces().size()];
		for (int i = 0; i < tableau.length; i++) {
			tableau[i] = lesEspeces.get(i).getNomEspece();
		}
		return tableau;
	}

	public String[] getTabRaceFromEspece(int index) {
		String[] tableau = new String[lesEspeces.get(index).getRaces().size()];
		for (int i = 0; i < tableau.length; i++) {
			tableau[i] = lesEspeces.get(index).getRaces().get(i);
		}
		return tableau;
	}

	public void supprimerAnimal(int selectedRow) throws BLLException {
		Animal animal = lesClients.get(getIndexClientEnCours()).getAnimaux().get(selectedRow);
		try {
			manager.supprimerAnimal(animal);
			lesClients.get(getIndexClientEnCours()).getAnimaux().remove(selectedRow);
		} catch (BLLException e) {
			e.printStackTrace();
			throw new BLLException("Erreur de suppression");
		}
	}	
	
	/////////////
	// GESTION DU PERSONNEL
	/////////////

	public void connectionUtilisateur(String login, String mdp) throws BLLException {

		if (!login.isEmpty() && !mdp.isEmpty()) {
			try {
				setUtilisateurConnecté(manager.connecter(login, mdp));
			} catch (DalException e) {
				e.printStackTrace();
				throw new BLLException("Erreur dans le Login ou dans le Mot De Passe");
			}
		}

	}

	public List<Personnel> getPersonnel() {
		// TODO Auto-generated method stub
		lePersonnel = manager.getPersonnel();
		return lePersonnel;
	}

	public String[] getTabPersonnel() {
		// TODO Auto-generated method stub
		lePersonnel = getPersonnel();
		String[] tableau = new String[lePersonnel.size()];
		for (int i = 0; i < tableau.length; i++) {
			tableau[i] = new String(lePersonnel.get(i).getNom());
			do {
				tableau[i] = tableau[i] + " ";
			} while (tableau[i].length() < 40);
			tableau[i] = tableau[i] + lePersonnel.get(i).getRole() + "    *************";
		}
		return tableau;
	}

	public void deconnectionUtilisateur() {
		utilisateurConnecté = null;
	}

	public List<Personnel> getVeterinaires() {
		return lesVeterinaires;
	}

	public String[] getTabNomsVeterinaires() {
		String[] tableau = new String[lesVeterinaires.size()];
		for (int i = 0; i < tableau.length; i++) {
			tableau[i] = lesVeterinaires.get(i).getNom();
		}
		return tableau;
	}

	////////////
	// GESTION DES RDV
	////////////

	public List<RDV> getRDVJour(Date date, int indexVeterinaire) throws BLLException {
		lesRdv = manager.getRDVdu(date, lesVeterinaires.get(indexVeterinaire));
		return lesRdv;
	}

	public void supprimerRdvCourant(int index) throws BLLException {
		RDV rdvASupprimer = lesRdv.get(index);

		try {
			manager.supprimerRdv(rdvASupprimer);
			lesRdv.remove(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ajouterRdvCourant(int indexClient, int indexAnimal, int indexVeto, LocalDateTime dateRdv)
			throws BLLException, CreneauDejaPrisException {
		RDV nouveauRdv = new RDV(dateRdv, lesClients.get(indexClient).getNomClient(),
				lesClients.get(indexClient).getAnimaux().get(indexAnimal).getNomAnimal(),
				lesClients.get(indexClient).getAnimaux().get(indexAnimal).getEspece(),
				lesVeterinaires.get(indexVeto).getCodePers(),
				lesClients.get(indexClient).getAnimaux().get(indexAnimal).getCodeAnimal());
		try {

			manager.ajouterRdv(nouveauRdv);

			lesRdv.add(nouveauRdv);
		} catch (CreneauDejaPrisException e) {
			e.printStackTrace();
			throw new CreneauDejaPrisException("RDV deja existant");
		} catch (BLLException e) {
			e.printStackTrace();
			throw new BLLException("erreur clinique");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	
	

	

}
