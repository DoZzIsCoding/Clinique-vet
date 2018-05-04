package fr.eni.clinique.exceptions;

public class RdvNotFoundException extends Exception {

	public RdvNotFoundException() {
		super("Le rendez-vous n'existe pas");
	}
}
