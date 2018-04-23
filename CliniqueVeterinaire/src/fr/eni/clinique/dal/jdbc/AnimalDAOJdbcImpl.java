package fr.eni.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dal.AnimalDAO;
import fr.eni.clinique.dal.DalException;

public class AnimalDAOJdbcImpl implements AnimalDAO {

	private static final String SELECT_ALL = "SELECT codeAnimal, nomAnimal, sexe, couleur, race, espece, codeClient, tatouage, antecedents, archive FROM animaux;";
	
	
	@Override
	public Animal selectionnerUn(int id) {
		// TODO Auto-generated method stub
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

	private Animal itemBuilder(ResultSet rs) throws SQLException {
		Animal animal = new Animal(rs.getInt("CodeAnimal"), rs.getString("NomAnimal"),
									rs.getString("Sexe"), rs.getString("Couleur"), rs.getString("Race"),
									rs.getString("Espece"), rs.getInt("CodeClient"), rs.getString("Tatouage"), 
									rs.getString("Antecedents"));
		
		return animal;
	}

	@Override
	public void ajouter(Animal animal) {
		// TODO Auto-generated method stub
		
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

	
	
}
