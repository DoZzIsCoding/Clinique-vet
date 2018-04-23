package fr.eni.clinique.configuration;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Parametres {
	//Nom du fichier avec package et sans extension
	private static final String BUNDLE_NAME = "fr.eni.clinique.configuration.configuration"; //$NON-NLS-1$

	//Objet qui va lire le contenu du fichier
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Parametres() {
	}
	
	public static String getValue(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
