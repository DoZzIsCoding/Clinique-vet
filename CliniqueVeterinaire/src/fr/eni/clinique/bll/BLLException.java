package fr.eni.clinique.bll;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class BLLException extends Exception {

	private ArrayList<Exception> exceptions = new ArrayList<>();
	
	public BLLException() {
		
	}
	
	public BLLException(String message) {
		super(message);
	}

	public void ajouterException(Exception e) {
		exceptions.add(e);
	}
	
	public boolean hasExceptions() {
		return exceptions.size() > 0;
	}

	@Override
	public String getMessage() {
		StringBuilder builder = new StringBuilder(super.getMessage());
		builder.append(System.lineSeparator());
		for(Exception e : exceptions) {
			builder.append(e.getMessage()).append(System.lineSeparator());
		}
		
		return builder.toString();
	}
	
	
}
