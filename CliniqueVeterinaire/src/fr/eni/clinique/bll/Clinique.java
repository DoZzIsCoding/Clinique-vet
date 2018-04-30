package fr.eni.clinique.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Espece;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.RDV;
import fr.eni.clinique.dal.CreneauDejaPrisException;
import fr.eni.clinique.dal.DalException;

public class Clinique {

	private List<Client> lesClients;
	private List<Personnel> lesVeterinaires;
	private List<RDV> lesRdv;
	private List<Espece> lesEspeces;

	private int indexClientEnCours;
	private int indexAnimalEnCours;

	private Personnel utilisateurConnecté;

	private CliniqueManager manager;

	private Clinique() throws BLLException {
		manager = CliniqueManager.getInstance();
		lesClients = manager.getClients();
		lesVeterinaires = manager.getVeterinaires();
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
	// GESTION DES CLIENTS AVEC LEURS ANIMAUX
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

	public void ajouterAnimal(int indexclient, Animal animal) {

		try {
			animal.setCodeClient(lesClients.get(indexclient).getCodeClient());
			manager.ajouterAnimal(animal);
			lesClients.get(indexclient).ajouterAnimal(animal);
		} catch (DalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/////////////////
	// GESTION DES ANIMAUX
	/////////////////

	public List<Espece> getEspeces() {
		lesEspeces = manager.getEspeces();
		return lesEspeces;

	}

	public String[] getTabEspeces() {

		String[] tableau = new String[lesEspeces.size()];
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

	/////////////
	// GESTION DU PERSONNEL
	/////////////

	public void connectionUtilisateur(String login, String mdp) throws DalException {

		if (!login.isEmpty() && !mdp.isEmpty()) {
			setUtilisateurConnecté(manager.connecter(login, mdp));
		}

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
