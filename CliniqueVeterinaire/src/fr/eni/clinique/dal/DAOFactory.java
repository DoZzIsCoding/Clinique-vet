package fr.eni.clinique.dal;

import fr.eni.clinique.configuration.Parametres;
import fr.eni.clinique.dal.jdbc.AnimalDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.ClientDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.PersonnelDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.RDVDAOJdbcImpl;

public abstract class DAOFactory {
	
	public static AnimalDAO getAnimalDAO()
	{
		switch (Parametres.getValue("typeSauvegarde")) {
		case "jdbc":
			return new AnimalDAOJdbcImpl();
		default:
			return new AnimalDAOJdbcImpl();
		}
	}
	
	
	public static ClientDAO getClientDAO()
	{
		switch (Parametres.getValue("typeSauvegarde")) {
		case "jdbc":
			return new ClientDAOJdbcImpl();
		default:
			return new ClientDAOJdbcImpl();
		}
	}
	
	
	public static PersonnelDAO getPersonnelDAO()
	{
		switch (Parametres.getValue("typeSauvegarde")) {
		case "jdbc":
			return new PersonnelDAOJdbcImpl();
		default:
			return new PersonnelDAOJdbcImpl();
		}
	}


	public static RDVDAOJdbcImpl getRDVDAO() {
		switch (Parametres.getValue("typeSauvegarde")) {
		case "jdbc":
			return new RDVDAOJdbcImpl();
		default:
			return new RDVDAOJdbcImpl();
		}
	}
	
}
