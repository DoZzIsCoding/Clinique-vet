package fr.eni.clinique.dal;

public class DalException extends Exception {

	public DalException(String message) {
		super("DalException: " + message);
	}
}
