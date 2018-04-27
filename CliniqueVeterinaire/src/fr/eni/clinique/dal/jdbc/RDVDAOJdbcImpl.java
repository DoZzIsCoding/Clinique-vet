package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.RDV;
import fr.eni.clinique.dal.CreneauDejaPrisException;
import fr.eni.clinique.dal.DalException;
import fr.eni.clinique.dal.RDVDAO;

public class RDVDAOJdbcImpl implements RDVDAO {

	private static final String SELECT_AGENDA_JOUR = " select DateRdv, NomClient, prenomClient, NomAnimal, Espece, codeVeto, ag.codeAnimal from Agendas ag "
			+ "join Animaux an on an.CodeAnimal = ag.CodeAnimal " + "join Clients cl on cl.codeclient = an.Codeclient "
			+ "where codeveto = ? And DATEDIFF(day, ? , ag.DateRdv ) = 0";

	private static final String DELETE_RDV = "DELETE FROM Agendas WHERE DATEDIFF(day, ? , DateRdv ) = 0 "
			+ "AND DATEDIFF(hour, ? , DateRdv ) = 0" + "AND DATEDIFF(minute, ? , DateRdv ) = 0" + "AND codeVeto = ?";

	private static final String INSERT_RDV = "INSERT INTO Agendas(CodeVeto, DateRdv, CodeAnimal) VALUES (?,?,?);";
	
	@Override
	public RDV selectionnerUn(int id) throws DalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RDV> selectionnerTout() throws DalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ajouter(RDV value) throws DalException, CreneauDejaPrisException {
		try (Connection cnx = ConnectionDAO.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_RDV);
			Timestamp ts = Timestamp.valueOf(value.getDate());
			pstmt.setInt(1, value.getCodeVeto());
			pstmt.setTimestamp(2, ts);
			pstmt.setInt(3, value.getCodeAnimal());
			pstmt.executeUpdate();
		}catch (SQLServerException e) {
			e.printStackTrace();
			throw new CreneauDejaPrisException("un Rdv existe deja a cette heure");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DalException("erreur d'insertion de rendez vous");
		}

	}

	@Override
	public void modifier(RDV value) throws DalException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean supprimer(RDV value) throws DalException {

		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On considère qu'on a une connexion opérationnelle
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_RDV);

			// TODO: faire la conversion de LocalDateTime vers Sql.date sans
			// perdre les heures et minutes
			System.out.println("avant parametres");
			Timestamp ts = Timestamp.valueOf(value.getDate());
			System.out.println(ts);
			pstmt.setTimestamp(1, ts);
			pstmt.setTimestamp(2, ts);
			pstmt.setTimestamp(3, ts);
			pstmt.setInt(4, value.getCodeVeto());
			System.out.println(pstmt.executeUpdate() + " Enregistrement supprimé");
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<RDV> selectionnerRDV(Date date, Personnel veterinaire) throws DalException {
		List<RDV> agenda = new ArrayList<>();

		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On considère qu'on a une connexion opérationnelle
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_AGENDA_JOUR);
			pstmt.setInt(1, veterinaire.getCodePers());
			pstmt.setDate(2, utilToSqlDate(date));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				agenda.add(this.itemBuilder(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
