package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.clinique.configuration.Parametres;

public abstract class ConnectionDAO {
	public static Connection getConnection() throws SQLException
	{
		String url=Parametres.getValue("url"); 
		String user=Parametres.getValue("user"); 
		String pwd=Parametres.getValue("password");
		//Obtenir une connection
		Connection cnx = DriverManager.getConnection(url, user, pwd);
		return cnx;
	}
}
