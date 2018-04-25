package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dal.DalException;
import fr.eni.clinique.dal.PersonnelDAO;

public class PersonnelDAOJdbcImpl implements PersonnelDAO {

	private static final String SELECT_ALL = "SELECT codeClient, NomClient, prenomClient, adresse1, adresse2, codePostal, ville, numTel, assurance, email, remarque FROM clients where archive=0";
	private static final String SELECT_VETOS = "select codepers, nom, role from personnels where archive=0 and role='Vet'";

	@Override
	public Personnel selectionnerUn(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personnel> selectionnerTout() throws DalException {
		List<Personnel> personnel = new ArrayList<>();

		try (Connection cnx = ConnectionDAO.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			while (rs.next()) {
				personnel.add(this.itemBuilder(rs));
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DalException("select all");
		}
		return personnel;
	}

	@Override
	public void ajouter(Personnel personnel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifier(Personnel personnel) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean supprimer(Personnel personnel) {
		// TODO Auto-generated method stub
		return false;
	}

	// TODO: mercredi

	private Personnel itemBuilder(ResultSet rs) throws SQLException {
		Personnel personnel = new Personnel(rs.getInt("codepers"), rs.getString("nom"), "*****" ,rs.getString("role"));

		return personnel;
	}

	@Override
	public List<Personnel> selectionnerVetos() throws DalException {
		List<Personnel> personnel = new ArrayList<>();

		try (Connection cnx = ConnectionDAO.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_VETOS);

			while (rs.next()) {
				personnel.add(this.itemBuilder(rs));
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DalException("select vetos");
		}
		return personnel;
	}
}
