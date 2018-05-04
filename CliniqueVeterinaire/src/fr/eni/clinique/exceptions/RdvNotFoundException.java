package fr.eni.clinique.exceptions;

@SuppressWarnings("serial")
public class RdvNotFoundException extends Exception {

	public RdvNotFoundException() {
		super("Le rendez-vous n'existe pas");
	}
}
