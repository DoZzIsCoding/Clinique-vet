package fr.eni.clinique.test;

import java.util.GregorianCalendar;

import fr.eni.clinique.bo.Agenda;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;

public class TestBO {

	public static void main(String[] args) {


		Agenda rdv = new Agenda(1, new GregorianCalendar(2018, 2, 2, 15, 0), 12);
		
		
		System.out.println(rdv);
		
		//Animal animal = new Animal(2, "Banjo", "F", "bleu", "flouch", "a déterminer", 12, "klhsdjlgbqhs");
		//System.out.println(animal);
//		
//		Client client  = new Client(2, "nom", "prenom", "adresse1", "adresse2", "56000", "vannes", "0202020202", "maaf", "toto@gmail.com", "");
//		client.ajouterAnimal(animal);
//		System.out.println(client);
//		
//		Personnel veterinaire = new Personnel(1, "nomVeto", "pass", "veto");
//		System.out.println(veterinaire);
	}

}
