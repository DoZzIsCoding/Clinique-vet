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
import fr.eni.clinique.dal.DalException;

public class AnimalDAOJdbcImpl implements AnimalDAO {

	private static final String SELECT_ALL = "SELECT codeAnimal, nomAnimal, sexe, couleur, race, espece, codeClient, tatouage, antecedents, archive FROM animaux";
	
	private static final String SELECT_BY_ID = SELECT_ALL + " WHERE codeAnimal =?";	
	
	private static final String INSERT = "INSERT INTO ANIMAL(nomAnimal, sexe, couleur, race, espece, codeClient, tatouage, antecedents, archive ) "
										+ "VALUES(?,?,?,?,?,?,?,?,?)";;

	
	
	@Override
	public Animal selectionnerUn(int id) {
		Animal animal = null;
		
		try (Connection cnx = ConnectionDAO.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				animal = this.itemBuilder(rs);
			}
			return animal;
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<Animal> selectionnerTout() throws DalException {
		List<Animal> animaux = new ArrayList<>();
		
		try(Connection cnx = ConnectionDAO.getConnection()){
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			
			while(rs.next()){
				animaux.add(this.itemBuilder(rs));
			}
		}catch (SQLException e) {
			
		}
		return animaux;
	}


	@Override
	public void ajouter(Animal animal) {
		/*if(animal == null){
			throw NullPointerException();
		}*/
		
		try (Connection cnx = ConnectionDAO.getConnection()){
			PreparedStatement pstmt = cnx.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);
			// reste le resultset, et l'inssertion à faire demain :)
		
		}
		
	}

	@Override
	public void modifier(Animal animal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean supprimer(Animal animal) {
		// TODO Auto-generated method stub
		return false;
	}

	private Animal itemBuilder(ResultSet rs) throws SQLException {
		Animal animal = new Animal(rs.getInt("CodeAnimal"), rs.getString("NomAnimal"),
				rs.getString("Sexe"), rs.getString("Couleur"), rs.getString("Race"),
				rs.getString("Espece"), rs.getInt("CodeClient"), rs.getString("Tatouage"), 
				rs.getString("Antecedents"));
		
		return animal;
	}
	
	
}
