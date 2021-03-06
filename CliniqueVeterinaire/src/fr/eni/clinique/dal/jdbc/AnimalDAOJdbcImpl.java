package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dal.AnimalDAO;
import fr.eni.clinique.exceptions.DalException;

public class AnimalDAOJdbcImpl implements AnimalDAO {

	private static final String SELECT_ALL = "SELECT codeAnimal, nomAnimal, sexe, couleur, race, espece, codeClient, tatouage, antecedents, archive FROM animaux WHERE archive = 0;";

	private static final String INSERT = "INSERT INTO ANIMAUX(nomAnimal, sexe, couleur, race, espece, codeClient, tatouage, antecedents, archive ) "
			+ "VALUES(?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE = "UPDATE ANIMAUX SET " + "nomAnimal=?, " + "sexe=?, " + "couleur=?, "
			+ "race=?, " + "espece=?, " + "codeClient=?, " + "tatouage=?, " + "antecedents=?, " + "archive=? "
			+ "WHERE codeAnimal=? ;";

	private static final String DELETE = "UPDATE ANIMAUX SET archive=1 WHERE codeAnimal=? ;";

	@Override
	public Animal selectionnerUn(int id) {
		// non utilis�
		return null;
	}

	@Override
	/**
	 * s�l�ctionne tous les animaux non archiv�s
	 */
	public List<Animal> selectionnerTout() throws DalException {
		List<Animal> animaux = new ArrayList<>();

		try (Connection cnx = ConnectionDAO.getConnection()) {
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			while (rs.next()) {
				animaux.add(itemBuilder(rs));
			}
		} catch (SQLException e) {

		}
		return animaux;
	}

	@Override
	public void ajouter(Animal animal) throws DalException {
		if (animal == null) {
			throw new NullPointerException();
		}

		try (Connection cnx = ConnectionDAO.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			preparerStatement(animal, pstmt);
			pstmt.executeUpdate();
			ResultSet rsId = pstmt.getGeneratedKeys();
			if (rsId.next()) {
				animal.setCodeAnimal(rsId.getInt(1));
			}
		} catch (SQLException e) {
			throw new DalException("insert");
		}
	}

	@Override
	public void modifier(Animal animal) throws DalException {
		try (Connection cnx = ConnectionDAO.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			preparerStatement(animal, pstmt);

			pstmt.setInt(10, animal.getCodeAnimal());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new DalException("update");
		}

	}

	@Override
	/**
	 * change le status de l'animal � archiver
	 */
	public boolean supprimer(Animal animal) throws DalException {
		try (Connection cnx = ConnectionDAO.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, animal.getCodeAnimal());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DalException("delete");
		}
	}

	//////////////////////////
	// UTILITAIRES
	//////////////////////////
	public void traiterAnimal(Animal a) throws DalException {
		if (a.getCodeAnimal() == -1) {
			try {
				ajouter(a);
			} catch (DalException e) {
				throw new DalException("erreur ajout animal dans la base");
			}
		} else {
			try {
				modifier(a);
			} catch (DalException e) {
				throw new DalException("erreur modification animal dans la base");
			}
		}
	}

	public static Animal itemBuilder(ResultSet rs) throws SQLException {
		Animal animal = new Animal(rs.getInt("CodeAnimal"), rs.getString("NomAnimal"), rs.getString("Sexe").charAt(0),
				rs.getString("Couleur"), rs.getString("Race"), rs.getString("Espece"), rs.getInt("CodeClient"),
				rs.getString("Tatouage"), rs.getString("Antecedents"));

		return animal;
	}

	private void preparerStatement(Animal animal, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, animal.getNomAnimal());
		pstmt.setString(2, String.valueOf(animal.getSexe()));
		pstmt.setString(3, animal.getCouleur());
		pstmt.setString(4, animal.getRace());
		pstmt.setString(5, animal.getEspece());
		pstmt.setInt(6, animal.getCodeClient());
		pstmt.setString(7, animal.getTatouage());
		pstmt.setString(8, animal.getAntecedents());
		pstmt.setBoolean(9, animal.isArchive());
	}

}
