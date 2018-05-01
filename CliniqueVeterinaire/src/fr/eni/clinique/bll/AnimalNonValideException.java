package fr.eni.clinique.bll;

@SuppressWarnings("serial")
public class AnimalNonValideException extends Exception {

	private String messageGlobal;
	
	public AnimalNonValideException() {
		super();
		
	}
	
	public void ajouterErreur(String msg) {
		messageGlobal += msg + System.lineSeparator();
	}
	
	public String getMessageGlobal() {
		return messageGlobal;
	}
}
