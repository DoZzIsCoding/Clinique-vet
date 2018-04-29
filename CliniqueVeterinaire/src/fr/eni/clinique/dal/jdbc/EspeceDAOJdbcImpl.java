package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Espece;
import fr.eni.clinique.bo.RDV;
import fr.eni.clinique.dal.CreneauDejaPrisException;
import fr.eni.clinique.dal.DalException;
import fr.eni.clinique.dal.EspeceDAO;

public class EspeceDAOJdbcImpl implements EspeceDAO {

	private static final String SELECT_ALL = "Select * from Races order by Espece";
	

	@Override
	public Espece selectionnerUn(int id) throws DalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Espece> selectionnerTout() throws DalException {

		List<Espece> especes = new ArrayList<>();
		
		try (Connection cnx = ConnectionDAO.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			String EspeceEnCours = new String();
			int indexEspeceEnCours = 0;
			while (rs.next()) {
				if(rs.getString("Espece").equals(especes.get(indexEspeceEnCours).getNomEspece())){
					EspeceEnCours = rs.getString("Espece");
					especes.get(indexEspeceEnCours).ajouterRace(rs.getString("Espece"));
					indexEspeceEnCours++;
				}
				else{
					especes.get(indexEspeceEnCours).ajouterRace(rs.getString("Espece"));
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return especes;
	}

	@Override
	public void ajouter(Espece value) throws DalException, CreneauDejaPrisException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifier(Espece value) throws DalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supprimer(Espece value) throws DalException {
		// TODO Auto-generated method stub
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
