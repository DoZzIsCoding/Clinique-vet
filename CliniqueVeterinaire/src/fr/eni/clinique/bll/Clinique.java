package fr.eni.clinique.bll;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.RDV;

public class Clinique {
	
private List<Client> lesClients;
private List<Personnel> lesVeterinaires;
private List<RDV> lesRdv;
	
	private CliniqueManager manager;
	
	private Clinique() throws BLLException {
		manager = CliniqueManager.getInstance();
		lesClients = manager.getClients();
		lesVeterinaires = manager.getVeterinaires();
	}
	
	////////////////////////
	// SINGLETON
	////////////////////////
	private static Clinique instance = null;
	
	public static Clinique getInstance() throws BLLException {
		if(instance == null) {
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
	public String[] getTabNomsClients(){
		String[] tableau= new String[this.getClients().size()];
		for (int i = 0; i < tableau.length; i++) {
			tableau[i] = lesClients.get(i).getNomClient() + " " + lesClients.get(i).getPrenomClient();
		}
		return tableau;
	}
	
	
	/**
	 * 
	 * @param client complet (avec Liste d'animaux)
	 * @return un tableau de String contenant les noms des animaux du client.
	 */
	public String[] getAnimauxDeClient(int index){
		String[] tableau = new String[lesClients.get(index).getAnimaux().size()];
		for (int i = 0; i < tableau.length; i++) {
			tableau[i] = lesClients.get(index).getAnimaux().get(i).getNomAnimal();
		}
		return tableau;
	}
	
	
	
	
	/////////////
	// GESTION DU PERSONNEL
	/////////////

	public List<Personnel> getVeterinaires() {
		return lesVeterinaires;
	}
	
	public String[] getTabNomsVeterinaires(){
		String[] tableau= new String[lesVeterinaires.size()];
		for (int i = 0; i < tableau.length; i++) {
			tableau[i] = lesVeterinaires.get(i).getNom();
		}
		return tableau;
	}
	
	////////////
	// GESTION DES RDV
	////////////
	
	public List<RDV> getRDVJour(Date date, int indexVeterinaire) throws BLLException {
		lesRdv =  manager.getRDVdu(date, lesVeterinaires.get(indexVeterinaire));
		return lesRdv;
	}
	
	public void supprimerRdvCourant(int index) throws BLLException{
		RDV rdvASupprimer = lesRdv.get(index);
	
		try {
			manager.supprimerRdv(rdvASupprimer);
			lesRdv.remove(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ajouterRdvCourant(int indexClient, int indexAnimal, int indexVeto, LocalDateTime dateRdv) throws BLLException{
		RDV nouveauRdv = new RDV(dateRdv, 
									lesClients.get(indexClient).getNomClient(), 
									lesClients.get(indexClient).getAnimaux().get(indexAnimal).getNomAnimal(),
									lesClients.get(indexClient).getAnimaux().get(indexAnimal).getEspece(), 
									lesVeterinaires.get(indexVeto).getCodePers(), 
									lesClients.get(indexClient).getAnimaux().get(indexAnimal).getCodeAnimal());
		try {
			
			manager.ajouterRdv(nouveauRdv);
			
			lesRdv.add(nouveauRdv);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	
}
