package fr.eni.clinique.exceptions;

@SuppressWarnings("serial")
public class AnimalNotFoundException extends Exception {

	
	public AnimalNotFoundException() {
		super("Aucun animal pour ce client");
	}
}
