package fr.eni.clinique.dal;

import java.util.Date;
import java.util.List;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.RDV;
import fr.eni.clinique.exceptions.DalException;

public interface RDVDAO extends DAO<RDV>{

	
	/**
	 * les enregistrement contiennent: 
	 * Une heure de RDV,
	 * Le Nom du client,
	 * le nom de l'animal,
	 * l'espeve de l'animal
	 * @param date
	 * @param veterinaire
	 * @return la liste des RDV du veterinaire pour la date donnée
	 * @throws DalException 
	 */
	public abstract List<RDV> selectionnerRDV(Date date, Personnel veterinaire) throws DalException ;
	
}
