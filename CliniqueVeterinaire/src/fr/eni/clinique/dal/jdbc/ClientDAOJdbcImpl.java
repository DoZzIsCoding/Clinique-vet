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

	private static final String SELECT_ALL = "SELECT codeClient, NomClient, prenomClient, adresse1, adresse2, codePostal, ville, numTel, assurance, email, remarque FROM clients where archive=0";
	private static final String SELECT_BY_ID = SELECT_ALL + "and codeClient=?";
	private static final String INSERT = "INSERT INTO Clients(codeClient, nom, prenomClient, "
			+ "adresse1, adresse2, codePostal, " + "ville, numTel, assurance, email, archive ) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "update clients SET nom=?" + "prenomClient=?" + "adresse1=?" + "adresse2=?"
			+ "codePostal=?" + "ville=?" + "numtel=?" + "assurance=?" + "email=?" + "remarque=?" + "archive=?"
			+ "where codeclient=?";
	private static final String DELETE = "delete from clients where codeClient=?";
	public Client selectionnerUn(int id) throws DalException {
		Client client = null;

		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On consid�re qu'on a une connexion op�rationnelle
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

		try (Connection cnx = ConnectionDAO.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			while (rs.next()) {
				clients.add(this.itemBuilder(rs));
			}
		} catch (SQLException e) {

		}
		return clients;
	}

	private Client itemBuilder(ResultSet rs) throws SQLException {
		Client client = new Client(rs.getInt("codeClient"), rs.getString("nomclient"), rs.getString("prenomClient"),
				rs.getString("adresse1"), rs.getString("adresse2"), rs.getString("codePostal"),
				rs.getString("codeClient"), rs.getString("numTel"), rs.getString("assurance"), rs.getString("email"),
				rs.getString("remarque"));

		return client;
	}

	public void ajouter(Client client) throws DalException {
		if (client == null) {
			throw new NullPointerException();
		}
		// ici, j'ai un article forc�ment non null
		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On consid�re qu'on a une connexion op�rationnelle
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			// Ajout des param�tres � la requ�te
			preparerParametres(client, pstmt);
			// Ex�cution de la requ�te
			pstmt.executeUpdate();
			// R�cup�ration de l'id g�n�r�
			ResultSet rsId = pstmt.getGeneratedKeys();
			if (rsId.next()) {
				client.setCodeClient(rsId.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("insert");
		}
	}

	private void preparerParametres(Client client, PreparedStatement pstmt) throws SQLException {

		pstmt.setString(1, client.getNomClient());
		pstmt.setString(2, client.getPrenomClient());
		pstmt.setString(3, client.getAdresse1());
		pstmt.setString(4, client.getAdresse2());
		pstmt.setString(5, client.getCodePostal());
		pstmt.setString(6, client.getVille());
		pstmt.setString(7, client.getNumTel());
		pstmt.setString(8, client.getAssurance());
		pstmt.setString(9, client.getEmail());
		pstmt.setString(10, client.getRemarque());
		pstmt.setBoolean(11, client.getArchive());

	}

	public void modifier(Client client) throws DalException {

		if (client == null) {
			throw new NullPointerException();
		}
		// ici, j'ai un client forc�ment non null
		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On consid�re qu'on a une connexion op�rationnelle
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			// Ajout des param�tres � modifier en base � la requ�te
			preparerParametres(client, pstmt);
			// Ajout du crit�re de restriction
			pstmt.setInt(12, client.getCodeClient());
			// Ex�cution de la requ�te
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("update");
		}
	}

	public boolean supprimer(Client client) {
		boolean suppressionOK = false;
		if (client == null) {
			throw new NullPointerException();
		}
		// ici, j'ai un client forc�ment non null
		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On consid�re qu'on a une connexion op�rationnelle
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			// Ajout du crit�re de restriction
			pstmt.setInt(1, client.getCodeClient());
			// Ex�cution de la requ�te
			pstmt.executeUpdate();
			suppressionOK = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return suppressionOK;
	}

}