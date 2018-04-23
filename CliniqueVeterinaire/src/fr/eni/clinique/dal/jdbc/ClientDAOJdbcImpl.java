package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dal.ClientDAO;
import fr.eni.clinique.dal.DalException;
import fr.eni.clinique.dal.jdbc.ConnectionDAO;

public class ClientDAOJdbcImpl implements ClientDAO {

	private static final String SELECT_ALL = "SELECT codeClient, nom, prenomClient, adresse1, adresse2, codePostal, ville, numTel, assurance, email, archive FROM clients ";
	private static final String SELECT_BY_ID = SELECT_ALL + "where codeClient=?";
	private static final String INSERT = "INSERT INTO Clients(codeClient, nom, prenomClient, "
																+ "adresse1, adresse2, codePostal, "
																+ "ville, numTel, assurance, email, archive ) " 
																+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	
	public Client selectionnerUn(int id) throws DalException {
		Client client = null;

		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On considère qu'on a une connexion opérationnelle
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				client = this.itemBuilder(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("selectById");
		}

		return client;
	}

	public List<Client> selectionnerTout() throws DalException {
		List<Client> clients = new ArrayList<>();
		
		try(Connection cnx = ConnectionDAO.getConnection()){
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			
			while(rs.next()){
				clients.add(this.itemBuilder(rs));
			}
		}catch (SQLException e) {
			
		}
		return clients;
	}

	private Client itemBuilder(ResultSet rs) throws SQLException {
		Client client = new Client(rs.getInt("codeClient"), 
				rs.getString("nom"), 
				rs.getString("prenomClient"), 
				rs.getString("adresse1"), 
				rs.getString("adresse2"), 
				rs.getString("codePostal"), 
				rs.getString("codeClient"), 
				rs.getString("numTel"), 
				rs.getString("assurance"), 
				rs.getString("email"), 
				rs.getString("remarque"));
		
		return client;
	}

	public void ajouter(Client client) throws DalException {
		if (client == null) {
			throw new NullPointerException();
		}
		// ici, j'ai un article forcément non null
		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On considère qu'on a une connexion opérationnelle
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			// Ajout des paramètres à la requête
			preparerParametres(client, pstmt);
			// Exécution de la requête
			pstmt.executeUpdate();
			// Récupération de l'id généré
			ResultSet rsId = pstmt.getGeneratedKeys();
			if (rsId.next()) {
				client.setCodeClient(rsId.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("insert");
		}
	}

	private void preparerParametres(Client client, PreparedStatement pstmt) {
			
	}

	public void modifier(Client client) {
		// TODO Auto-generated method stub
		
	}

	public boolean supprimer(Client client) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
