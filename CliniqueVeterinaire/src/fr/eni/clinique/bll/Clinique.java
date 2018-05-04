package fr.eni.clinique.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Espece;
import fr.eni.clinique.bo.Observable;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.RDV;
import fr.eni.clinique.exceptions.AnimalNonValideException;
import fr.eni.clinique.exceptions.BLLException;
import fr.eni.clinique.exceptions.ClientNonValideException;
import fr.eni.clinique.exceptions.CreneauDejaPrisException;
import fr.eni.clinique.exceptions.DalException;
import fr.eni.clinique.exceptions.DateAnterieureException;

public class Clinique {

	private List<Client> lesClients;
	private List<Personnel> lesVeterinaires;
	private List<Personnel> lePersonnel;
	private List<RDV> lesRdv;
	private List<Espece> lesEspeces;
	private List<Client> laRecherche;

	private Observable<Integer> indexAnimalEnCours;
	private Observable<Integer> indexClientEnCours;
	private Observable<Boolean> listeMiseAJour;
	
	private Personnel utilisateurConnecté = null;

	private CliniqueManager manager;

	private Clinique() throws BLLException {
		manager = CliniqueManager.getInstance();
		lesClients = manager.getClients();
		lesVeterinaires = manager.getVeterinaires();
		indexClientEnCours = new Observable<>(0);
		indexAnimalEnCours = new Observable<>(0);
		listeMiseAJour = new Observable<>(false);
	}

	// GETTERS SETTERS
	
	

	public Personnel getUtilisateurConnecté() {
		return utilisateurConnecté;
	}

	public void setUtilisateurConnecté(Personnel utilisateurConnecté) {
		this.utilisateurConnecté = utilisateurConnecté;
	}

	public int getIndexClientEnCours() {
		return indexClientEnCours.getValeur();
	}

	public void setIndexClientEnCours(int index) {
		indexClientEnCours.setValeur(index);;
	}

	public int getIndexAnimalEnCours() {
		return indexAnimalEnCours.getValeur();
	}

	public void setIndexAnimalEnCours(int index) {
		indexAnimalEnCours.setValeur(index);
	}
	
	public Observable<Integer> getIndexClientObserve(){
		return indexClientEnCours;
	}
	
	public Observable<Integer> getIndexAnimalObserve(){
		return indexClientEnCours;
	}
	
	public Observable<Boolean> getListeMiseAJourObserve(){
		return listeMiseAJour;
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
	
	/**
	 * 
	 * @return la liste des client (animaux inclus)
	 */
	public List<Client> getClients() {
		try {
			lesClients = manager.getClients();
			return lesClients;
		} catch (BLLException e) {
		}
		return null;
	}
	
	public void setClientEncours(int index) {
		setIndexClientEnCours(index);
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
		if(index == -1){
			index=0;
		}
		String[] tableau = new String[lesClients.get(index).getAnimaux().size()];
		for (int i = 0; i < tableau.length; i++) {
			tableau[i] = lesClients.get(index).getAnimaux().get(i).getNomAnimal();
		}
		return tableau;
	}

	public void traiterAnimal(Animal animal) throws AnimalNonValideException, BLLException {

		manager.traiterAnimal(animal);
		getClients();
		setIndexAnimalEnCours(getClientEnCours().getAnimaux().size()-1);

		listeMiseAJour.setValeur(true);
	}

	public Client getClientEnCours() {
		if (getIndexClientEnCours() == -1) {
			return null;
		} else {
			return getClients().get(getIndexClientEnCours());
		}
	}

	public Animal getAnimalEnCours() {
		if (getIndexClientEnCours() == -1 || getIndexAnimalEnCours() == -1) {
			return null;
		} else {
			return lesClients.get(getIndexClientEnCours()).getAnimaux().get(getIndexAnimalEnCours());
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
		laRecherche = new ArrayList<>();
		for (Client c : lesClients) {
			if(c.getNomClient().toLowerCase().indexOf(mot.toLowerCase())!=-1 || c.getPrenomClient().toLowerCase().indexOf(mot.toLowerCase())!=-1)
				laRecherche.add(c);
		}
		return laRecherche;
	}
	
/**
 * retourne l'index de l'objet rentré dans lesClients selectionné dans la liste de recherche
 * @param client
 * @return
 */
	public void selectionnerClient(Client client) {

		int i = 0;
		Boolean codeTrouve = false;
		do {
			if(lesClients.get(i).getCodeClient() == client.getCodeClient()) {
				setIndexClientEnCours(i);
			}
		} while (!codeTrouve && i < lesClients.size());
	}
	
	public void supprimerClientCourant() throws BLLException {
		Client clientASupprimer = lesClients.get(getIndexClientEnCours());
		
		try {
			manager.supprimerClient(clientASupprimer);
			lesClients.remove(getIndexClientEnCours());
			listeMiseAJour.setValeur(true);
		} catch (BLLException e) {
			throw new BLLException("Erreur : suppression client non effectuée");
		}
		
	}

	
	public void traiterClient(Client client) throws BLLException, ClientNonValideException {
		try {
			manager.traiterClient(client);
			if(client.getCodeClient()==-1){
				setClientEncours(lesClients.size()-1);
			}
			getClients();
			listeMiseAJour.setValeur(true);
		} catch (BLLException e) {
			throw new BLLException("Erreur accès aux données");
		}
	}
	
	/////////////////
	// GESTION DES ANIMAUX
	/////////////////

	public List<Espece> getEspeces() {
		try {
			lesEspeces = manager.getEspeces();
		} catch (DalException e) {
		}
		return lesEspeces;

	}

	public String[] getTabEspeces() {
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

			listeMiseAJour.setValeur(true);
		} catch (BLLException e) {
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
				throw new BLLException("Erreur dans le Login ou dans le Mot De Passe");
			}
		}

	}

	public List<Personnel> getPersonnel() {
		try {
			lePersonnel = manager.getPersonnel();
		} catch (DalException e) {
		}
		return lePersonnel;
	}

	public String[] getTabPersonnel() {
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
			throw new BLLException("Erreur lors de la suppression du RDV");
		}
	}

	public void ajouterRdvCourant(int indexClient, int indexAnimal, int indexVeto, LocalDateTime dateRdv)
			throws BLLException, CreneauDejaPrisException, DateAnterieureException {
		
		RDV nouveauRdv = new RDV(dateRdv, lesClients.get(indexClient).getNomClient(),
				lesClients.get(indexClient).getAnimaux().get(indexAnimal).getNomAnimal(),
				lesClients.get(indexClient).getAnimaux().get(indexAnimal).getEspece(),
				lesVeterinaires.get(indexVeto).getCodePers(),
				lesClients.get(indexClient).getAnimaux().get(indexAnimal).getCodeAnimal());
		try {

			manager.ajouterRdv(nouveauRdv);

			lesRdv.add(nouveauRdv);
		} catch (CreneauDejaPrisException e) {
			throw new CreneauDejaPrisException("RDV deja existant");
		} catch (BLLException e) {
			throw new BLLException("erreur clinique");
		}
	}

	

	
	

	

}
