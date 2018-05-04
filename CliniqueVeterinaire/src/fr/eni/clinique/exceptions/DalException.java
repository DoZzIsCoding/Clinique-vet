package fr.eni.clinique.exceptions;

@SuppressWarnings("serial")
public class DalException extends Exception {

	public DalException(String message) {
		super("DalException: " + message);
	}
}
