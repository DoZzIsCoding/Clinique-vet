package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.RDV;
import fr.eni.clinique.dal.DalException;
import fr.eni.clinique.dal.RDVDAO;

public class RDVDAOJdbcImpl implements RDVDAO {

	private static final String SELECT_AGENDA_JOUR = "  SELECT CodeVeto, DateRDV, Codeanimal FROM Agendas "
			+ "where codeveto = ? " + "And DATEDIFF(day, dateRdv, ? ) = 0" ;

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
	public void ajouter(RDV value) throws DalException {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifier(RDV value) throws DalException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean supprimer(RDV value) throws DalException {
		// TODO Auto-generated method stub
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
			if (rs.next()) {
				agenda.add(this.itemBuilder(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("selectById");
		}

		return agenda;
	}

	private RDV itemBuilder(ResultSet rs) {
		return null;
		// TODO: bin le builder

	}

	private java.sql.Date utilToSqlDate(java.util.Date date) {

		return new java.sql.Date(date.getTime());

	}

}
