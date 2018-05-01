package fr.eni.clinique.bll;

@SuppressWarnings("serial")
public class ClientNonValideException extends Exception {

	private String messageGlobal;
	
	public ClientNonValideException() {
		super();
		
	}
	
	public void ajouterErreur(String msg) {
		messageGlobal += msg + System.lineSeparator();
	}
	
	public String getMessageGlobal() {
		return messageGlobal;
	}
}
