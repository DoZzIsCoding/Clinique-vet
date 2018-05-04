package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Espece;
import fr.eni.clinique.bo.RDV;
import fr.eni.clinique.dal.EspeceDAO;
import fr.eni.clinique.exceptions.CreneauDejaPrisException;
import fr.eni.clinique.exceptions.DalException;

public class EspeceDAOJdbcImpl implements EspeceDAO {

	private static final String SELECT_ALL = "Select Race,Espece from Races order by Espece";
	

	@Override
	public Espece selectionnerUn(int id) throws DalException {
		return null;
	}

	@Override
	public List<Espece> selectionnerTout() throws DalException {

		List<Espece> especes = new ArrayList<>();
		
		try (Connection cnx = ConnectionDAO.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			String EspeceEnCours = new String("");
			while (rs.next()) {
				if(!EspeceEnCours.equals(rs.getString("Espece"))){
					especes.add(new Espece(rs.getString("Espece")));
					especes.get(especes.size()-1).ajouterRace(rs.getString("Race"));
					EspeceEnCours = rs.getString("Espece");
				}
				else{
					especes.get(especes.size()-1).ajouterRace(rs.getString("Race"));
				}

			}
		} catch (SQLException e) {
		}
		return especes;
	}

	@Override
	public void ajouter(Espece value) throws DalException, CreneauDejaPrisException {
		
	}

	@Override
	public void modifier(Espece value) throws DalException {
		
	}

	@Override
	public boolean supprimer(Espece value) throws DalException {
		return false;
	}


	
	private RDV itemBuilder(ResultSet rs) throws SQLException {
		RDV rdv = new RDV(rs.getTimestamp("DateRDV").toLocalDateTime(),
				rs.getString("Nomclient") + " " + rs.getString("prenomClient"), rs.getString("NomAnimal"),
				rs.getString("Espece"), rs.getInt("CodeVeto"), rs.getInt("CodeAnimal"));

		return rdv;
	}

	private java.sql.Date utilToSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}


}
