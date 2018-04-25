package fr.eni.clinique.dal;

import java.util.Date;
import java.util.List;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.RDV;

public interface RDVDAO extends DAO<RDV>{

	
	/**
	 * 
	 * @param date
	 * @param veterinaire
	 * @return la liste des RDV du veterinaire pour la date donnée
	 */
	public abstract List<RDV> selectionnerRDV(Date date, Personnel veterinaire) ;
	
}
