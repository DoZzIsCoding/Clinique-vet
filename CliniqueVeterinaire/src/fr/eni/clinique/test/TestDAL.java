package fr.eni.clinique.test;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dal.AnimalDAO;
import fr.eni.clinique.dal.DAOFactory;

public class TestDAL {

	public static void main(String[] args) {

		try {
			AnimalDAO animalDAO = DAOFactory.getAnimalDAO();
			
			System.out.println("Liste apres selectall");
			for(Animal a:animalDAO.selectionnerTout())
			{
				System.out.println(a);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}

}
