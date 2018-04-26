package fr.eni.clinique.bll;

public class RdvNotFoundException extends Exception {

	public RdvNotFoundException() {
		super("Le rendez-vous n'existe pas");
	}
}
