package fr.eni.clinique.bll;

@SuppressWarnings("serial")
public class AnimalNotFoundException extends Exception {

	
	public AnimalNotFoundException() {
		super("Aucun animal pour ce client");
	}
}
