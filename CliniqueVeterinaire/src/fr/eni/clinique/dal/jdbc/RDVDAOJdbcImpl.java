package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.RDV;
import fr.eni.clinique.dal.RDVDAO;
import fr.eni.clinique.exceptions.CreneauDejaPrisException;
import fr.eni.clinique.exceptions.DalException;

public class RDVDAOJdbcImpl implements RDVDAO {

	private static final String SELECT_AGENDA_JOUR = " select DateRdv, NomClient, prenomClient, NomAnimal, Espece, codeVeto, ag.codeAnimal from Agendas ag "
			+ "join Animaux an on an.CodeAnimal = ag.CodeAnimal " + "join Clients cl on cl.codeclient = an.Codeclient "
			+ "where codeveto = ? And DATEDIFF(day, ? , ag.DateRdv ) = 0";

	private static final String DELETE_RDV = "DELETE FROM Agendas WHERE DATEDIFF(day, ? , DateRdv ) = 0 "
			+ "AND DATEDIFF(hour, ? , DateRdv ) = 0" + "AND DATEDIFF(minute, ? , DateRdv ) = 0" + "AND codeVeto = ?";

	private static final String INSERT_RDV = "INSERT INTO Agendas(CodeVeto, DateRdv, CodeAnimal) VALUES (?,?,?);";
	
	private static final String VERIF_RDV = "SELECT * from Agendas where (CodeVeto=? and DATEDIFF(minute, ? , DateRdv ) = 0) or (CodeAnimal = ? and DATEDIFF(minute, ? , DateRdv ) = 0)";
	
	@Override
	public RDV selectionnerUn(int id) throws DalException {
		return null;
	}

	@Override
	public List<RDV> selectionnerTout() throws DalException {
		return null;
	}

	@Override
	public void ajouter(RDV value) throws DalException, CreneauDejaPrisException {
		verifierRDV(value);
		
		try (Connection cnx = ConnectionDAO.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_RDV);
			Timestamp ts = Timestamp.valueOf(value.getDate());
			pstmt.setInt(1, value.getCodeVeto());
			pstmt.setTimestamp(2, ts);
			pstmt.setInt(3, value.getCodeAnimal());
			pstmt.executeUpdate();
		}catch (SQLServerException e) {
			throw new CreneauDejaPrisException("un Rdv existe deja a cette heure");
		} catch (Exception e) {
			throw new DalException("erreur d'insertion de rendez vous");
		}

	}

	private void verifierRDV(RDV value) throws CreneauDejaPrisException {
		try (Connection cnx = ConnectionDAO.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(VERIF_RDV);
			Timestamp ts = Timestamp.valueOf(value.getDate());
			pstmt.setInt(1, value.getCodeVeto());
			pstmt.setTimestamp(2, ts);
			pstmt.setInt(3, value.getCodeAnimal());
			pstmt.setTimestamp(4, ts);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				throw new CreneauDejaPrisException("Le Veterinaire n'est pas disponible a cette heure");
			}
		}catch (SQLServerException e) {
			throw new CreneauDejaPrisException("un Rdv existe deja a cette heure");
		} catch (SQLException e1) {
		}
	}

	@Override
	public void modifier(RDV value) throws DalException {
	}

	@Override
	public boolean supprimer(RDV value) throws DalException {

		try (Connection cnx = ConnectionDAO.getConnection()) {
			
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_RDV);

			Timestamp ts = Timestamp.valueOf(value.getDate());
			pstmt.setTimestamp(1, ts);
			pstmt.setTimestamp(2, ts);
			pstmt.setTimestamp(3, ts);
			pstmt.setInt(4, value.getCodeVeto());
			
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public List<RDV> selectionnerRDV(Date date, Personnel veterinaire) throws DalException {
		List<RDV> agenda = new ArrayList<>();

		try (Connection cnx = ConnectionDAO.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_AGENDA_JOUR);
			pstmt.setInt(1, veterinaire.getCodePers());
			pstmt.setDate(2, utilToSqlDate(date));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				agenda.add(this.itemBuilder(rs));
			}
		} catch (SQLException e) {
			throw new DalException("selectById");
		}

		return agenda;
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
