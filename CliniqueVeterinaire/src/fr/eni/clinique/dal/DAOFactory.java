package fr.eni.clinique.dal;

import fr.eni.clinique.configuration.Parametres;
import fr.eni.clinique.dal.jdbc.AnimalDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.ClientDAOJdbcImpl;

public abstract class DAOFactory {
	
	public static AnimalDAO getAnimalDAO()
	{
		switch (Parametres.getValue("typeSauvegarde")) {
		case "jdbc":
			return new AnimalDAOJdbcImpl();
//		case "serial":
//			return new ArticleDAOSerialImpl();
		default:
			return new AnimalDAOJdbcImpl();
		}
	}
	
	
	public static ClientDAO getClientDAO()
	{
		switch (Parametres.getValue("typeSauvegarde")) {
		case "jdbc":
			return new ClientDAOJdbcImpl();
//		case "serial":
//			return new ArticleDAOSerialImpl();
		default:
			return new ClientDAOJdbcImpl();
		}
	}
	
}
