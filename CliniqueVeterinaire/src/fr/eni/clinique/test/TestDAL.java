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
			Animal animal = new Animal(50, "toto", "F", "blouage", "Batard", "Chien", 7, "KHP98R", "");
			System.out.println("Sel�ction par id");
			System.out.println(animalDAO.selectionnerUn(1));
			
			System.out.println("Liste apr�s ajout");
			animalDAO.ajouter(animal);
			for(Animal a:animalDAO.selectionnerTout()){
				
				System.out.println(a);
			}
			
			System.out.println("Liste apr�s modif");
			animal = animalDAO.selectionnerUn(1);
			animal.setCouleur("Bleu");
			animalDAO.modifier(animal);
			System.out.println(animalDAO.selectionnerUn(1));
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
//		try {
//			ClientDAO clientDAO = DAOFactory.getClientDAO();
//			
//			System.out.println("Liste apres selectall");
//			System.out.println(clientDAO.selectionnerTout());
//			for(Client c: clientDAO.selectionnerTout())
//			{
//				System.out.println(c);
//			}
//			Client client= new Animal(50, "toto", "F", "blouage", "Batard", "Chien", 7, "KHP98R", "");
//			System.out.println("Sel�ction par id");
//			System.out.println(animalDAO.selectionnerUn(1));
//			
//			System.out.println("Liste apr�s ajout");
//			animalDAO.ajouter(animal);
//			for(Animal a:animalDAO.selectionnerTout()){
//				
//				System.out.println(a);
//			}
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}

	}

}
