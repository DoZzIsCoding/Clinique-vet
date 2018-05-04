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
import fr.eni.clinique.exceptions.DalException;

public class ClientDAOJdbcImpl implements ClientDAO {

	private static final String SELECT_ALL = "SELECT codeClient, NomClient, prenomClient, adresse1, adresse2, codePostal, ville, numTel, assurance, email, remarque FROM clients where archive=0";
	private static final String SELECT_BY_ID = SELECT_ALL + "and codeClient=?";
	private static final String INSERT = "INSERT INTO Clients(NomClient, prenomClient, "
			+ "adresse1, adresse2, codePostal, " + "ville, numTel, assurance, email, remarque, archive ) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "update clients SET nomClient=?," 
			+ "prenomClient=?," 
			+ "adresse1=?," 
			+ "adresse2=?,"
			+ "codePostal=?," 
			+ "ville=?," 
			+ "numtel=?," 
			+ "assurance=?," 
			+ "email=?," 
			+ "remarque=?," 
			+ "archive=? "
			+ "where codeclient=?";
	
	private static final String DELETE = "UPDATE CLIENTS SET archive=1 WHERE codeClient=?;"
										+ "UPDATE ANIMAUX SET archive=1 WHERE codeClient=?;";
	
	private static final String SELECT_WITH_ANIMALS =  "SELECT * FROM clients cli left join animaux ani on ani.CodeClient = cli.CodeClient where cli.Archive = 0 order by cli.CodeClient";
	
	/**
	 * selectionne un client sans ses animaux
	 */
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
			throw new DalException("selectById");
		}
		return client;
	}

	public List<Client> selectionnerTout(){
		//non utilisé
		return null;
	}
	
	public List<Client> selectionnerAvecAnimaux() throws DalException {
		List<Client> clients = new ArrayList<>();

		try (Connection cnx = ConnectionDAO.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_WITH_ANIMALS);
			int cliEnCours = -1;
			
			@SuppressWarnings("unused")// utilisé dans la boucle while
			int testNull;
			while (rs.next()) {
				if(rs.getInt("codeclient") != cliEnCours){
					cliEnCours = rs.getInt("codeclient");
					clients.add(this.itemBuilder(rs));
					testNull=rs.getInt("Codeanimal");
					if(!rs.wasNull() && !rs.getBoolean("Archive")){
						clients.get(clients.size()-1).ajouterAnimal(AnimalDAOJdbcImpl.itemBuilder(rs));
					}
				}
				else{
					if(!rs.getBoolean("Archive")){
						clients.get(clients.size()-1).ajouterAnimal(AnimalDAOJdbcImpl.itemBuilder(rs));
					}
				}
			}
		} catch (SQLException e) {
		}
		return clients;
	}


	private Client itemBuilder(ResultSet rs) throws SQLException {
		Client client = new Client(rs.getInt("codeClient"), rs.getString("nomclient"), rs.getString("prenomClient"),
				rs.getString("adresse1"), rs.getString("adresse2"), rs.getString("codePostal"),
				rs.getString("Ville"), rs.getString("numTel"), rs.getString("assurance"), rs.getString("email"),
				rs.getString("remarque"));

		return client;
	}

	public void ajouter(Client client) throws DalException {
		if (client == null) {
			throw new NullPointerException();
		}
		try (Connection cnx = ConnectionDAO.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			preparerParametres(client, pstmt);
			pstmt.executeUpdate();
			ResultSet rsId = pstmt.getGeneratedKeys();
			if (rsId.next()) {
				client.setCodeClient(rsId.getInt(1));
			}
		} catch (SQLException e) {
			throw new DalException("insert");
		}
	}



	public void modifier(Client client) throws DalException {

		if (client == null) {
			throw new NullPointerException();
		}
		try (Connection cnx = ConnectionDAO.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			preparerParametres(client, pstmt);
			pstmt.setInt(12, client.getCodeClient());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DalException("update");
		}
	}

	public boolean supprimer(Client client) throws DalException {
		boolean suppressionOK = false;
		if (client == null) {
			throw new NullPointerException();
		}
		try (Connection cnx = ConnectionDAO.getConnection()) {
			
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, client.getCodeClient());
			pstmt.setInt(2, client.getCodeClient());
			pstmt.executeUpdate();
			suppressionOK = true;
		} catch (SQLException e) {
			throw new DalException("Erreur de suppression dans la base de données");
		}
		return suppressionOK;
	}
	
	///////////////////////
	//UTILITAIRES
	///////////////////////
	
	public void traiterClient(Client c) throws DalException{
		if(c.getCodeClient()==-1){
			ajouter(c);
		}else{
			modifier(c);
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

}
