package fr.eni.clinique.dal;

import fr.eni.clinique.configuration.Parametres;
import fr.eni.clinique.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.clinique.dal.jdbc.PanierDaoJdbcImpl;
import fr.eni.clinique.dal.serial.ArticleDAOSerialImpl;

public abstract class DAOFactory {
	
	public static AnimalDAO getArticleDAO()
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
	
}
