package fr.eni.clinique.test;

import java.sql.Date;
import java.time.LocalDate;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.RDV;
import fr.eni.clinique.dal.DAOFactory;
import fr.eni.clinique.dal.RDVDAO;

public class TestDAL {

	public static void main(String[] args) {

//		try {
//			AnimalDAO animalDAO = DAOFactory.getAnimalDAO();
//			
//			System.out.println("Liste apres selectall");
//			for(Animal a:animalDAO.selectionnerTout())
//			{
//				System.out.println(a);
//			}
//			Animal animal = new Animal(50, "toto", "F", "blouage", "Batard", "Chien", 7, "KHP98R", "");
//			System.out.println("Seléction par id");
//			System.out.println(animalDAO.selectionnerUn(1));
//			
//			System.out.println("Liste après ajout");
//			animalDAO.ajouter(animal);
//			for(Animal a:animalDAO.selectionnerTout()){
//				
//				System.out.println(a);
//			}
//			
//			System.out.println("Liste après modif");
//			animal = animalDAO.selectionnerUn(1);
//			animal.setCouleur("Bleu");
//			animalDAO.modifier(animal);
//			System.out.println(animalDAO.selectionnerUn(1));
//			
//			
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
		try {
			RDVDAO RdvDAO = DAOFactory.getRDVDAO();
			Personnel veto = new Personnel(2, "nomtest", "***", "vet");
			System.out.println("Liste apres select RDV");
			for(RDV r: RdvDAO.selectionnerRDV(Date.valueOf(LocalDate.of(2018, 4, 24)), veto))
			{
				System.out.println(r);
				System.out.println("une ligne");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
