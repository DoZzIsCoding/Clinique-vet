package fr.eni.clinique.exceptions;

public class DalException extends Exception {

	public DalException(String message) {
		super("DalException: " + message);
	}
}
