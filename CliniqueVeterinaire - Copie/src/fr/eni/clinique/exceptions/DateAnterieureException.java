package fr.eni.clinique.exceptions;

@SuppressWarnings("serial")
public class DateAnterieureException extends Exception {

	public DateAnterieureException(String message) {
		super(message);
	}
}
