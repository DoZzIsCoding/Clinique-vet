package fr.eni.clinique.exceptions;

@SuppressWarnings("serial")
public class ClientNonValideException extends Exception {

	private String messageGlobal;
	
	public ClientNonValideException() {
		super();
		messageGlobal = new String();
	}
	
	public void ajouterErreur(String msg) {
		messageGlobal += msg + System.lineSeparator();
	}
	
	public String getMessageGlobal() {
		return messageGlobal;
	}
}
